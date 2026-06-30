public class Calcular {

    private static final double K = 8.99e9;

    private double L;
    private double P;
    private double Q;
    private double q;
    private double h;

    public Calcular(double L, double P, double Q, double q, double h) {
        this.L = L;
        this.P = P;
        this.Q = Q;
        this.q = q;
        this.h = h;
    }

    public double calcularX() {
        double forcaEsq = K * q * Q / (h * h);
        double forcaDir = K * 2 * q * Q / (h * h);
        double x = L / 2.0 + (forcaDir - forcaEsq) * (L / 2.0) / P;
        return x;
    }

    public double calcularH() {
        double h2 = 3.0 * K * q * Q / P;
        return Math.sqrt(h2);
    }

    public double getL() { return L; }
    public void setL(double L) { this.L = L; }
    public double getP() { return P; }
    public void setP(double P) { this.P = P; }
    public double getQ() { return Q; }
    public void setQ(double Q) { this.Q = Q; }
    public double getQ_minusculo() { return q; }
    public void setQ_minusculo(double q) { this.q = q; }
    public double getH() { return h; }
    public void setH(double h) { this.h = h; }
}
