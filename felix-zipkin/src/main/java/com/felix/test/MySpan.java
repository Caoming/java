package com.felix.test;

import java.util.Map;

public class MySpan {
    private String traceId;

    private String parentId;
    private String id;
    private String name;
    private long timestamp;
    private long duration;
    private String kind;
    private EndPoint localEndPoint, remoteEndPoint ;
    private Map<String, String> tags;

    public static enum Kind{
        CLIENT,
        SERVER,
        PRODUCER,
        CONSUMER
    }

    public static class EndPoint{
        String serviceName,ipv4,ipv6;
        byte[] ipv4Bytes,ipv6Bytes;
        int port;

        public EndPoint(String serviceName) {
            this.serviceName = serviceName;
        }
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public EndPoint getLocalEndPoint() {
        return localEndPoint;
    }

    public void setLocalEndPoint(EndPoint localEndPoint) {
        this.localEndPoint = localEndPoint;
    }

    public EndPoint getRemoteEndPoint() {
        return remoteEndPoint;
    }

    public void setRemoteEndPoint(EndPoint remoteEndPoint) {
        this.remoteEndPoint = remoteEndPoint;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
}
