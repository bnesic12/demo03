package com.bnesic12.demo03.dto;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DNode {
    private boolean visited;
    private int value;
    private DNode prev;
    private String name;

    public Map<DNode, Integer> getNeighEdges() {
        return neighEdges;
    }

    private Map<DNode, Integer> neighEdges;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public DNode getPrev() {
        return prev;
    }

    public void setPrev(DNode prev) {
        this.prev = prev;
    }

    public DNode(String name) {
        visited = false;
        value = Integer.MAX_VALUE;
        prev = null;
        this.name = name;
        neighEdges = new HashMap<DNode, Integer>();
        System.out.println("DNode.ctor(): name="+name+"; end");
    }

    public boolean addEdge(DNode neigh, int weight) {
        boolean rc = false;
        try {
            if(weight<0) {
                throw new Exception("negative weight");
            }
            neighEdges.put(neigh, new Integer(weight));
            rc = true;
        } catch(Exception e) {
            System.err.println(e);
        }
        return rc;
    }

    public boolean editEdge(DNode neigh, int weight) {
        boolean rc = false;
        try {
            if(weight<0 || neigh==null) {
                throw new Exception("cannot edit edge");
            }
            neighEdges.remove(neigh);
            neighEdges.put(neigh, new Integer(weight));
            rc = true;
        } catch(Exception e) {
            System.err.println(e);
        }
        return rc;

    }

    public int getNeighWeight(DNode node) {
        int rc = 0;
        try {
            rc = neighEdges.get(node);
        } catch(Exception e) {
            System.err.println(e);
        }
        return rc;
    }

    public Set<DNode> getNeighs() {
        Set<DNode> neighs = neighEdges.keySet();
        return neighs;
    }

    public String getEdges() {
        String rc = "";
        Iterator<Map.Entry<DNode, Integer>> itr = neighEdges.entrySet().iterator();
        int i = 0;
        while(itr.hasNext()) {
            Map.Entry<DNode, Integer> entry = itr.next();
            rc+="i="+i+": neigb="+entry.getKey().getName()+", weight="+entry.getValue()+"; ";
            i++;
        }
        return rc;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        String csp = ", ";
        sb.append("name="+name);
        sb.append(csp);
        sb.append("value="+value);
        sb.append(csp);
        sb.append("visited="+visited);
        sb.append(csp);
        sb.append("prev=");
        if(prev!=null) {
            sb.append(prev.getName());
        } else {
            sb.append("none");
        }
        return sb.toString();

    }

    public void finalize() {
        System.out.println("DNode.finalize(): deleting node "+name);
    }
}
