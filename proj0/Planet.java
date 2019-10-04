public class Planet {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    static final double G = 6.67e-11;

    public  Planet(double xP, double yP,
                       double xV, double yV,
                       double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    public  Planet(Planet p){
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    public double calcDistance(Planet p){
        double xDisSqu = (p.xxPos - xxPos) * (p.xxPos - xxPos);
        double yDisSqu = (p.yyPos - yyPos) * (p.yyPos - yyPos);
        double retDis = Math.sqrt(xDisSqu + yDisSqu);
        return retDis;
    }

    public double calcForceExertedBy(Planet p){

        double dis = calcDistance(p);
        double force = (G * mass * p.mass)/(dis * dis);
        return force;
    }

    public double calcForceExertedByX(Planet p){
        double force = calcForceExertedBy(p);
        double dis = calcDistance(p);
        double disX = p.xxPos - xxPos;
        double forceX = (force * disX)/dis;
        return forceX;
    }

    public double calcForceExertedByY(Planet p){
        double force = calcForceExertedBy(p);
        double dis = calcDistance(p);
        double disY = p.yyPos - yyPos;
        double forceY = (force * disY)/dis;
        return forceY;
    }

    private boolean equals(Planet p){
        return (this == p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netForceX = 0;
        for (Planet p: allPlanets){
            if (!p.equals(this)){
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netForceY = 0;
        for (Planet p: allPlanets){
            if (!p.equals(this)){
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY){
        //calculate acceleration
        double aX = fX/mass;
        double aY = fY/mass;
        //update velocity
        xxVel += aX * dt;
        yyVel += aY * dt;
        //update pos
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw(){
        String filePath = "./images/"+imgFileName;
        StdDraw.picture(xxPos, yyPos, filePath);
    }




}
