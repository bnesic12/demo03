package com.bnesic12.demo03.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

// to use @ConfigurationProperties, must add configuration-processor into pom.xml
@Component
@ConfigurationProperties
@PropertySource("classpath:dNetStartup.properties")
public class DNet {

    public DNet() {

    }

    @PostConstruct
    public void postConstructDNet() {
        for(NetEdge edge : edges) {
            this.addEdge(edge.getFrom(), edge.getTo(), edge.getWeight(), edge.isBiDir());
        }
    }

    public List<NetEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<NetEdge> netEdges) {
        this.edges = netEdges;
    }

    private List<NetEdge> edges;

    //application.properties file:
    //edges[0].from=B
    //edges[0].to=C
    //edges[0].weight:3
    //edges[0].dir=bi
    public static class NetEdge {
        String from;
        String to;
        String weight;
        String dir;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public boolean isBiDir() {
            boolean rc=false;
            if(this.dir.equals("bi")) {
                rc=true;
            }
            return rc;
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("from:"+this.from+",to:"+this.to+",weight:"+this.weight+",dir:"+dir);
            return sb.toString();
        }
    }

    public Map<String, DNode> getNet() {
        return net;
    }

    public void setNet(Map<String, DNode> net) {
        this.net = net;
    }
    
    private Map<String, DNode> net;

    //application.properties file:
    //startNode=A
    private String startNode;

    //application.properties file:
    //destNode=F
    private String destNode;

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

    public void resetNet() {
        for(Map.Entry<String, DNode> entry : net.entrySet()) {
            DNode node = entry.getValue();
            if(entry.getKey().equals(startNode)) {
                node.setValue(0);
            } else {
                node.setValue(Integer.MAX_VALUE);
            }
            node.setVisited(false);
            node.setPrev(null);
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
        StringBuffer sb = new StringBuffer();
        try {
            DNode dest = net.get(destNode);
            ArrayList<StringBuffer> res = new ArrayList<>();
            res.add(new StringBuffer("-->" + dest.getName()));
            DNode prev = dest.getPrev();
            int tot = 0;
            while (prev != null) {
                int val = prev.getNeighWeight(dest);
                tot += val;
                if (prev.getName().equals(startNode)) {
                    res.add(new StringBuffer(prev.getName() + "-->" + val));
                } else {
                    res.add(new StringBuffer("-->" + prev.getName() + "-->" + val));
                }
                dest = prev;
                prev = dest.getPrev();
            }
            for (int i = res.size() - 1; i >= 0; i--) {
                sb.append(res.get(i));
            }
            sb.append(", tot: " + tot);
        } catch(Exception e) {
            sb.append("incomplete");
        }
        return sb.toString();
    }

}
