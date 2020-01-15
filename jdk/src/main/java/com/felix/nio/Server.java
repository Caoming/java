package com.felix.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private Selector selector;
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    String str;

    public void start() throws Exception{
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.bind(new InetSocketAddress("localhost",8001));

        selector = Selector.open();
        socketChannel.register(selector,SelectionKey.OP_ACCEPT);

        while (true){
            // 选择一个通道进行交互
            int select = selector.select();
            if(select == 0){
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()){
                SelectionKey key = keyIterator.next();
                if(!key.isValid()){
                    continue;
                }
                if(key.isAcceptable()){
                    accept(key);
                }else if(key.isReadable()){
                    read(key);
                }else if(key.isWritable()){
                    write(key);
                }

                keyIterator.remove();
            }
        }
    }

    /**
     * 接收客户端的请求
     * @param key
     * @throws Exception
     */
    public void accept(SelectionKey key) throws Exception{
        ServerSocketChannel ssc =  (ServerSocketChannel)key.channel();
        SocketChannel accept = ssc.accept();
        accept.configureBlocking(false);
        accept.register(selector,SelectionKey.OP_READ);
        System.out.println("a new client connected" + accept.getRemoteAddress());

    }

    public void read(SelectionKey key) throws Exception{
        SocketChannel socketChannel = (SocketChannel) key.channel();
        this.readBuffer.clear();
        int numRead ;
        try{
            numRead = socketChannel.read(this.readBuffer);
        }catch (Exception e){
            key.cancel();
            socketChannel.close();
            return;
        }
        str = new String(readBuffer.array(),0,numRead);
        System.out.println(str);
//        socketChannel.register(selector,SelectionKey.OP_WRITE);
        key.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);
    }

    /**
     *
     * @param key
     * @throws Exception
     */
    public void write(SelectionKey key) throws Exception{
        SocketChannel socketChannel = (SocketChannel) key.channel();
        System.out.println("write:"+ str);
        sendBuffer.clear();
        sendBuffer.put(str.getBytes());
        sendBuffer.flip();
        socketChannel.write(sendBuffer);
//        socketChannel.register(selector,SelectionKey.OP_READ);
        key.interestOps(SelectionKey.OP_READ|SelectionKey.OP_WRITE);

    }

    public static void main(String[] args) throws Exception{
        new Server().start();
    }
}
