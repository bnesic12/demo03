package com.bnesic12.demo03.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Component
public class DNet {

    public Map<String, DNode> getNet() {
        return net;
    }

    public void setNet(Map<String, DNode> net) {
        this.net = net;
    }

    private Map<String, DNode> net;
    private String startNode;
    private String destNode;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getStartNode() {
        return startNode;
    }

    public void setStartNode(String startNode) {
        this.startNode = startNode;
    }

    public String getDestNode() {
        return destNode;
    }

    public void setDestNode(String destNode) {
        this.destNode = destNode;
    }

    public DNet() {
        System.out.println("DNet.ctor(): start");
        net = new HashMap<>();
        path = "none";
        startNode = "A";
        destNode = "F";
        System.out.println("DNet.ctor(): end");
    }

    public void put(String node) {
        if(node!=null) {
            net.put(node, new DNode(node));
        }
    }

    public void addEdge(String fNode, String tNode, String w, boolean bi) {
        if(fNode!=null && tNode!=null) {
            if (bi) {
                net.get(fNode).addEdge(net.get(tNode), Integer.parseInt(w));
                net.get(tNode).addEdge(net.get(fNode), Integer.parseInt(w));
            }
        }
    }

    public void editEdge(String fNode, String tNode, String w, boolean bi) {
        if(fNode!=null && tNode!=null) {
            if (bi) {
                net.get(fNode).editEdge(net.get(tNode), Integer.parseInt(w));
                net.get(tNode).editEdge(net.get(fNode), Integer.parseInt(w));
            }
        }
    }

    private void showNet() {
        Iterator<Map.Entry<String, DNode>> itr = net.entrySet().iterator();
        int i = 0;
        while(itr.hasNext()) {
            String rc="";
            Map.Entry<String, DNode> entry = itr.next();
            rc+="i="+i+": "+entry.getValue()+"; EDGES: "+entry.getValue().getEdges();
            System.out.println(rc);
            i++;
        }
    }

    public void resetWeights() {
        Iterator<Map.Entry<String, DNode>> itr = net.entrySet().iterator();

        while(itr.hasNext()) {
            Map.Entry<String, DNode> entry = itr.next();
            entry.getValue().setValue(Integer.MAX_VALUE);
        }
    }

    public DNode getNode(String node) {
        return net.get(node);
    }

    public void calculatePath() {
        DNode curr = net.get(startNode); // A

        curr.setValue(0);
        showNet();
        boolean run = true;
        int check=0;
        while(run && check<1000) {
            check++;
            System.out.println(check+"--------");
            System.out.println("Current: "+curr.getName());
            curr.setVisited(true);

            Iterator<Map.Entry<DNode, Integer>> itr = curr.getNeighEdges().entrySet().iterator();
            while(itr.hasNext()) {
                Map.Entry<DNode, Integer> neigh = itr.next();
                int candidate = curr.getValue()+neigh.getValue();
                if(neigh.getKey().getValue()>candidate) {
                    neigh.getKey().setValue(candidate);
                    neigh.getKey().setPrev(curr);
                }
            }
            Iterator<Map.Entry<String, DNode>> itr1 = net.entrySet().iterator();
            int min=Integer.MAX_VALUE;
            DNode next = null;
            while(itr1.hasNext()) {
                Map.Entry<String, DNode> node = itr1.next();
                if(!node.getValue().isVisited() && !node.getValue().getName().equals(startNode)) {
                    if(node.getValue().getValue()<min) {
                        min=node.getValue().getValue();
                        next=node.getValue();
                    }
                }
            }

            if(next==null) {
                run=false;
            } else {
                curr = next;
            }

            showNet();
            System.out.println(check+"--------");
        }
    }

    public String toString() {
        path="";
        try {
            DNode dest = net.get(destNode); // F

            ArrayList<String> res = new ArrayList<>();
            res.add("-->" + dest.getName());
            DNode prev = dest.getPrev();
            int tot = 0;
            while (prev != null) {
                int val = prev.getNeighWeight(dest);
                tot += val;
                if (prev.getName().equals(startNode)) {
                    res.add(prev.getName() + "-->" + val);
                } else {
                    res.add("-->" + prev.getName() + "-->" + val);
                }
                dest = prev;
                prev = dest.getPrev();
            }
            for (int i = res.size() - 1; i >= 0; i--) {
                path += res.get(i);
            }
            path += ", tot: " + tot;
        } catch(Exception e) {}
        System.out.println(path);
        return path;
    }

    public void finalize() {
        System.out.println("DNet.finalize(): deleted");
    }

}
