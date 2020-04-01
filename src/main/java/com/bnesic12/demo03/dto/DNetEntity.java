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
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
        if(dNet!=null) {
            dNet.setStartNode(startNode);
        }
    }

    public String getDestNode() {
        return destNode;
    }

    public void setDestNode(String destNode) {
        this.destNode = destNode;
        if(dNet!=null) {
            dNet.setDestNode(destNode);
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String wAB = "5";
    private String wAC = "6";
    private String wBC = "3";
    private String wBE = "2";
    private String wBF = "10";
    private String wBD = "7";
    private String wCE = "7";
    private String wEF = "4";
    private String wDF = "2";

    public String getwAB() {
        return wAB;
    }

    public void setwAB(String wAB) {
        this.wAB = wAB;
        dNet.editEdge("A", "B", wAB, true);
    }

    public String getwAC() {
        return wAC;
    }

    public void setwAC(String wAC) {
        this.wAC = wAC;
        dNet.addEdge("A", "C", wAC, true);
    }

    public String getwBC() {
        return wBC;
    }

    public void setwBC(String wBC) {
        this.wBC = wBC;
        dNet.addEdge("B", "C", wBC, true);
    }

    public String getwBE() {
        return wBE;
    }

    public void setwBE(String wBE) {
        this.wBE = wBE;
        dNet.addEdge("B", "E", wBE, true);
    }

    public String getwBF() {
        return wBF;
    }

    public void setwBF(String wBF) {
        this.wBF = wBF;
        dNet.addEdge("B", "F", wBF, true);
    }

    public String getwBD() {
        return wBD;
    }

    public void setwBD(String wBD) {
        this.wBD = wBD;
        dNet.addEdge("B", "D", wBD, true);
    }

    public String getwCE() {
        return wCE;
    }

    public void setwCE(String wCE) {
        this.wCE = wCE;
        dNet.addEdge("C", "E", wCE, true);
    }

    public String getwEF() {
        return wEF;
    }

    public void setwEF(String wEF) {
        this.wEF = wEF;
        dNet.addEdge("E", "F", wEF, true);
    }

    public String getwDF() {
        return wDF;
    }

    public void setwDF(String wDF) {
        this.wDF = wDF;
        dNet.addEdge("D", "F", wDF, true);
    }

    public DNetEntity() {
        System.out.println("DNetEntity.ctor(): start");
        path = "none";
        setStartNode("A");
        setDestNode("F");
        System.out.println("DNetEntity.ctor(): end");
    }

    public void generateNet2() {
        System.out.println("DNetEntity.generateNet2(): start");

        dNet.put("A");
        dNet.put("B");
        dNet.put("C");
        dNet.put("D");
        dNet.put("E");
        dNet.put("F");


        dNet.addEdge("A", "B", wAB, true);
        dNet.addEdge("A", "C", wAC, true);
        dNet.addEdge("B", "C", wBC, true);
        dNet.addEdge("B", "E", wBE, true);
        dNet.addEdge("B", "D", wBD, true);
        dNet.addEdge("B", "F", wBF, true);
        dNet.addEdge("C", "E", wCE, true);
        dNet.addEdge("E", "F", wEF, true);
        dNet.addEdge("D", "F", wDF, true);
        System.out.println("DNetEntity.generateNet2(): end");
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
