package com.bnesic12.demo03.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DNetEntity {

    @Autowired
    private DNet dNet;

    private String startNode;
    private String destNode;
    private String path;

    public DNet getdNet() {
        return dNet;
    }

    public void setdNet(DNet dNet) {
        this.dNet = dNet;
    }

    public String getStartNode() {
        if(startNode == null && dNet != null) {
            startNode=dNet.getStartNode();
        }
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
        if(dNet!=null) {
            dNet.setStartNode(startNode);
        }
    }

    public String getDestNode() {
        if(destNode == null && dNet != null) {
            destNode=dNet.getDestNode();
        }
        return destNode;
    }

    public void setDestNode(String destNode) {
        this.destNode = destNode;
        if(dNet!=null) {
            dNet.setDestNode(destNode);
        }
    }

    public void resetNet() {
        dNet.resetNet();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DNetEntity() {
        System.out.println("DNetEntity.ctor(): start");
        path = "none";
        System.out.println("DNetEntity.ctor(): end");
    }

    public void calculatePath() {
        dNet.calculatePath();
        setPath(dNet.toString());
    }

    public String toString() {

        return getPath();
    }

    public static void main(String[] args) {
        DNetEntity dne = new DNetEntity();

    }
}
