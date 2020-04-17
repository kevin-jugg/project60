import java.lang.Math;
public class Body {
    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    static final double G = 6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Body(Body b) {
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double dx = b.xxPos - this.xxPos;
        double dy = b.yyPos - this.yyPos;
        double r = Math.sqrt(dx * dx + dy * dy);
        return r;
    }

    public double calcForceExertedBy(Body b) {
        double d = this.calcDistance(b);
        double force = G * this.mass * b.mass / (d * d);
        return force;
    }

    public double calcForceExertedByX(Body b) {
        double force = this.calcForceExertedBy(b);
        double d = this.calcDistance(b);
        double dx = b.xxPos - this.xxPos;
        double forceX = force * dx / d;
        return forceX;
    }

    public double calcNetForceExertedByX(Body[] bodys) {
        double totalDx = 0;
        for (int i = 0; i < bodys.length; i++) {
            if (this.equals(bodys[i])) {
                continue;
            }
            totalDx += this.calcForceExertedByX(bodys[i]);
        }
        return totalDx;
    }

    public double calcForceExertedByY(Body b) {
        double force = this.calcForceExertedBy(b);
        double d = this.calcDistance(b);
        double dy = b.yyPos - this.yyPos;
        double forceY = force * dy / d;
        return forceY;
    }

    public double calcNetForceExertedByY(Body[] bodys) {
        double totalDy = 0;
        for (int i = 0; i < bodys.length; i++) {
            if (this.equals(bodys[i])) {
                continue;
            }
            totalDy += this.calcForceExertedByY(bodys[i]);
        }
        return totalDy;
    }

    public void update(double dt, double fX, double fY) {
        double xxAcceleration = fX / this.mass;
        double yyAcceleration = fY / this.mass;
        this.xxVel = this.xxVel + dt * xxAcceleration;
        this.yyVel = this.yyVel + dt * yyAcceleration;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos,this.yyPos, "images/" + imgFileName);
    }
}