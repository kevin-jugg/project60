
public class NBody {
    public static String backGroundToDraw = "./images/starfield.jpg";
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("provide 3 arguments");
        }
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double r = readRadius(filename);
        Body[] bodies = readBodies(filename);
//java NBody 1.0 1.0 "./data/planets.txt"
        int time = 0;
        StdDraw.enableDoubleBuffering();

        StdDraw.setScale(-r, r);
        StdDraw.clear();
//        StdDraw.picture(0,0,backGroundToDraw);

        while (time <= T) {
            double[] xForces = new double[5];
            double[] yForces = new double[5];
            for (int i = 0; i < bodies.length; i++) {
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
            }
            for (int i = 0; i < bodies.length; i++) {
                bodies[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0,0,backGroundToDraw);
            for (Body b : bodies) {
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }



    }

    public static double readRadius(String s) {
        In in = new In(s);
        int n = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String s) {
        In in = new In(s);
        int n = in.readInt();
        double radius = in.readDouble();
        Body[] bodies = new Body[5];
        int i = 0;
        while (!in.isEmpty()) {
            if (i < 5) {
                double xP = in.readDouble();
                double yP = in.readDouble();
                double xV = in.readDouble();
                double yV = in.readDouble();
                double m = in.readDouble();
                String img = in.readString();
                Body b = new Body(xP, yP, xV, yV, m, img);
                bodies[i] = b;
                i++;
            }
            else {
                break;
            }
        }
        return bodies;
    }

}