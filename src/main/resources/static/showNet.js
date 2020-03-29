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

        var ptA = new point(100,200,"ptA","A");
        ptA.createPoint();
        ptA.drawPoint();
        var ptB = new point(200,100,"ptB","B");
        ptB.createPoint();
        ptB.drawPoint();
        var ptC = new point(200,300,"ptC","C");
        ptC.createPoint();
        ptC.drawPoint();
        var ptD = new point(300, 50,"ptD","D");
        ptD.createPoint();
        ptD.drawPoint();
        var ptE = new point(300,300,"ptE","E");
        ptE.createPoint();
        ptE.drawPoint();
        var ptF = new point(400,100,"ptF","F");
        ptF.createPoint();
        ptF.drawPoint();

        var lineAB = new line(ptA, ptB, "lineAB", 5, "bi");
        lineAB.createLine();
        lineAB.drawLine();

        var lineBD = new line(ptB, ptD, "lineBD", 7, "bi");
        lineBD.createLine();
        lineBD.drawLine();

        var lineDF = new line(ptD, ptF, "lineDF", 2, "bi");
        lineDF.createLine();
        lineDF.drawLine();

        var lineBF = new line(ptB, ptF, "lineBF", 10, "bi");
        lineBF.createLine();
        lineBF.drawLine();

        var lineEF = new line(ptE, ptF, "lineEF", 4, "bi");
        lineEF.createLine();
        lineEF.drawLine();

        var lineBE = new line(ptB, ptE, "lineBE", 2, "bi");
        lineBE.createLine();
        lineBE.drawLine();

        var lineCE = new line(ptC, ptE, "lineCE", 7, "bi");
        lineCE.createLine();
        lineCE.drawLine();

        var lineBC = new line(ptB, ptC, "lineBC", 3, "bi");
        lineBC.createLine();
        lineBC.drawLine();

        var lineAC = new line(ptA, ptC, "lineAC", 6, "bi");
        lineAC.createLine();
        lineAC.drawLine();
