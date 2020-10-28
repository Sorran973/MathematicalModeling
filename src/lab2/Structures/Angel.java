//package lab2.Structures;
//
//
//public class Angel {
//
//    private float cos;
//    private float angel;
//
//    public Angel(Point a, Point b, Point c){
//        int[] ab = new int[]{b.getX() - a.getX(), b.getY() - a.getY()};
//        int[] cb = new int[]{b.getX() - c.getX(), b.getY() - c.getY()};
//
//        cos = (float) ((ab[0]*cb[0] + ab[1]*cb[1])
//                / (Math.sqrt(Math.pow(ab[0], 2) + Math.pow(ab[1], 2)) * Math.sqrt(Math.pow(cb[0], 2) + Math.pow(cb[1], 2))));
//
//        angel = (float) Math.toDegrees(Math.acos(cos));
//    }
//
//    public float getCos() {
//        return cos;
//    }
//    public float getAngel() {
//        return angel;
//    }
//
//    @Override
//    public String toString() {
//        return String.valueOf(angel);
//    }
//}
