package com.felix.lucene;

import com.felix.model.GasInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

/**
 * @Author caoming
 * @Date: 2020/9/14 10:31
 */
public class DocumentTest {

    public static void main(String[] args) throws Exception{
        GasInfo gasInfo = new GasInfo("123","123","123");
        Document document = new Document();
        FieldType type = new FieldType();
        type.setStored(true);
//        type.setStoreTermVectorOffsets(true);
//        type.setStoreTermVectorPayloads(true);
//        type.setStoreTermVectorPositions(true);
//        type.setStoreTermVectors(true);
        type.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        document.add(new Field("gasName",gasInfo.getGasName(), type));
        document.add(new Field("gasId",gasInfo.getGasId(), type));
        document.add(new Field("gasCode",gasInfo.getGasCode(), type));

        FSDirectory fsDirectory = FSDirectory.open(FileSystems.getDefault().getPath("/Users/caoming/code/java/lucene/data"));
        StandardAnalyzer standardAnalyzer = new StandardAnalyzer();

        IndexWriter indexWriter = new IndexWriter(fsDirectory, new IndexWriterConfig(standardAnalyzer));
        indexWriter.addDocument(document);
        indexWriter.close();
    }
}
