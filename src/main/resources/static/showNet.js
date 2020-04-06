class point {
    createPoint() {
        document.getElementById("net2").innerHTML +="<text id=\""+this.lab+"\" style=\"stroke:#ae6352; fill: #none;\"/></text>";
        document.getElementById(this.lab).innerHTML=this.disp;
    }

    drawPoint() {
        document.getElementById(this.lab).setAttribute("x", this.x);
        document.getElementById(this.lab).setAttribute("y", this.y);
    }

    constructor(x,y,lab,disp) {
        this.x=x;
        this.y=y;
        this.lab=lab;
        this.disp=disp;
    }
}

class line {
    constructor(a, b, lab, weight, dir) {
        this.pt1=a;
        this.pt2=b;
        this.lab=lab;
        this.weight=weight;
        this.dir=dir;
    }

    drawLine() {
        if(this.dir==="bi") {
            var lineElem=document.getElementById(this.lab);
            lineElem.setAttribute("x1", this.pt1.x);
            lineElem.setAttribute("y1", this.pt1.y);
            lineElem.setAttribute("x2", this.pt2.x);
            lineElem.setAttribute("y2", this.pt2.y);

            var wElem = document.getElementById(this.lab+"Weight");
            wElem.setAttribute("x", (this.pt1.x+this.pt2.x)/2);
            wElem.setAttribute("y", (this.pt1.y+this.pt2.y)/2);
        }

    }

    createLine() {
        if(this.dir==="bi") {
            var svgElem=document.getElementById("net2");
            svgElem.innerHTML +="<line id=\""+this.lab+"\" style=\"stroke:#ff0000; fill: #none;\"/>"

            svgElem.innerHTML +="<text id=\""+this.lab+"Weight"+"\" style=\"stroke:#000000; fill: #none;\"/></text>";
            document.getElementById(this.lab+"Weight").innerHTML=this.weight;

        }
    }

}

// quick workaround for placement of nodes on the map
function getPoint(myNode) {
    var myPoint;
    if(myNode=="A") {
        myPoint = new point(100, 200, "pt"+myNode, myNode);
    } else if(myNode=="B") {
        myPoint = new point(200, 100, "pt"+myNode, myNode);
    } else if(myNode=="C") {
        myPoint = new point(200, 300, "pt"+myNode, myNode);
    } else if(myNode=="D") {
        myPoint = new point(300, 50, "pt"+myNode, myNode);
    } else if(myNode=="E") {
        myPoint = new point(300, 300, "pt"+myNode, myNode);
    } else if(myNode=="F") {
        myPoint = new point(400, 100, "pt"+myNode, myNode);
    } else {
        myPoint = new point(100, 100, "pt"+myNode, myNode);
    }
    myPoint.createPoint();
    myPoint.drawPoint();
    return myPoint;
}

var points = dNetNodes.map(getPoint);

function getLine(thisEdge) {
    var fromPoint;
    for(let i=0; i<points.length; i++) {
        if(points[i].disp==thisEdge.from) {
            fromPoint=points[i];
            break;
        }
    }
    var toPoint;
    for(let i=0; i<points.length; i++) {
        if(points[i].disp==thisEdge.to) {
            toPoint=points[i];
            break;
        }
    }
    var thisLine = new line(fromPoint,
                            toPoint,
                            "line"+thisEdge.from+thisEdge.to,
                            thisEdge.weight,
                            thisEdge.dir);
    thisLine.createLine();
    thisLine.drawLine();
}

var edges = dNetEdges.map(getLine);

