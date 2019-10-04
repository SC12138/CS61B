public class NBody {
    public static double readRadius(String planetFileName){
        In in = new In(planetFileName);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String planetFileName){
        In in = new In(planetFileName);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] allPlanets = new Planet[num];
        for (int i=0; i<num; i++){
            allPlanets[i] = new Planet(in.readDouble(), in.readDouble(),
                                        in.readDouble(), in.readDouble(),
                                        in.readDouble(), in.readString());
        }
        return allPlanets;
    }

    public static void main(String[] args){
        if(args.length != 3){
            StdOut.println("Please provide correct parameters");

        }
        double T = Double.parseDouble(args[0]);// or use Double.valueOf(string), new Double(String s)
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        int numPlanets = allPlanets.length;

        //draw picture
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        double tALL = 0;
        double[] xForces = new double[numPlanets];
        double[] yForces = new double[numPlanets];
        while(tALL < T){
            StdDraw.clear();
            //calculate xForce and yForces
            for (int i=0; i<numPlanets; i++){
                xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
                yForces[i] = allPlanets[i].calcNetForceExertedByY(allPlanets);
            }
            for (int i=0; i<numPlanets; i++){
                allPlanets[i].update(dt, xForces[i], yForces[i]);
                allPlanets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            tALL += dt;
        }
        StdOut.printf("%d\n", allPlanets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < allPlanets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }





    }

}
