import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Animacao extends JFrame {

    public Animacao(double L, double P, double Q, double q,
                    double x, double h, double resultX, double resultH) {
        setTitle("Resultado - Barra em Equilíbrio");
        setSize(800, 460);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        add(new AnimacaoPanel(L, P, Q, q, h, resultX, resultH));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private static class Esfera {
        int x, y, raio;
        Color cor;
        String rotulo;
        Esfera(int x, int y, int raio, Color cor, String rotulo) {
            this.x = x; this.y = y; this.raio = raio;
            this.cor = cor; this.rotulo = rotulo;
        }
    }

    static class AnimacaoPanel extends JPanel {

        private final double L, resultX, resultH;

        private static final int APOIO_X  = 380;
        private static final int BARRA_Y  = 200;
        private static final int BARRA_PX = 560;
        private static final int CHAO_Y   = 340;
        private static final int R        = 12; // raio padrão das esferas

        AnimacaoPanel(double L, double P, double Q, double q, double h,
                      double resultX, double resultH) {
            this.L = L;
            this.resultX = resultX;
            this.resultH = resultH;
            setBackground(new Color(245, 248, 252));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int esq = APOIO_X - BARRA_PX / 2;
            int dir = APOIO_X + BARRA_PX / 2;

            desenharChao(g2);
            desenharApoio(g2);

            g2.setColor(new Color(50, 50, 50));
            g2.setStroke(new BasicStroke(7f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2.drawLine(esq, BARRA_Y, dir, BARRA_Y);

            List<Esfera> esferas = List.of(
                    new Esfera(esq, BARRA_Y, R, new Color(220, 80, 80), "+q"),
                    new Esfera(dir, BARRA_Y, R, new Color(180, 50, 50), "+2q"),
                    new Esfera(esq, CHAO_Y - R, R, new Color(50, 140, 200), "+Q"),
                    new Esfera(dir, CHAO_Y - R, R, new Color(50, 140, 200), "+Q")
            );
            for (Esfera e : esferas) desenharEsfera(g2, e);

            desenharBloco(g2, esq);
            desenharResultados(g2);
        }

        private void desenharChao(Graphics2D g2) {
            g2.setColor(new Color(180, 180, 180));
            g2.fillRect(50, CHAO_Y, 660, 14);
            g2.setColor(new Color(130, 130, 130));
            for (int i = 50; i < 710; i += 14) {
                g2.drawLine(i, CHAO_Y, i - 10, CHAO_Y + 14);
            }
        }

        private void desenharApoio(Graphics2D g2) {
            int[] xs = {APOIO_X - 22, APOIO_X + 22, APOIO_X};
            int[] ys = {CHAO_Y, CHAO_Y, BARRA_Y};
            g2.setColor(new Color(100, 140, 200));
            g2.fillPolygon(xs, ys, 3);
            g2.setColor(new Color(60, 90, 140));
            g2.drawPolygon(xs, ys, 3);
            g2.setColor(new Color(60, 60, 60));
            g2.fillOval(APOIO_X - 5, BARRA_Y - 5, 10, 10);
        }

        private void desenharEsfera(Graphics2D g2, Esfera e) {
            g2.setColor(e.cor);
            g2.fillOval(e.x - e.raio, e.y - e.raio, e.raio * 2, e.raio * 2);
            g2.setColor(Color.WHITE);
            g2.drawOval(e.x - e.raio, e.y - e.raio, e.raio * 2, e.raio * 2);
            g2.setFont(new Font("Arial", Font.BOLD, 10));
            FontMetrics fm = g2.getFontMetrics();
            int larguraTexto = fm.stringWidth(e.rotulo);
            g2.drawString(e.rotulo, e.x - larguraTexto / 2, e.y + 4);
        }

        private void desenharBloco(Graphics2D g2, int esq) {
            double fracao = (L > 0) ? (resultX / L) : 0.5;
            fracao = Math.max(0.05, Math.min(0.95, fracao));
            int bx = esq + (int) (fracao * BARRA_PX);
            int largura = 44, altura = 36;

            g2.setColor(new Color(200, 200, 100));
            g2.fillRect(bx - largura / 2, BARRA_Y, largura, altura);
            g2.setColor(new Color(140, 140, 40));
            g2.drawRect(bx - largura / 2, BARRA_Y, largura, altura);

            g2.setColor(new Color(80, 80, 0));
            g2.setFont(new Font("Arial", Font.BOLD, 12));
            g2.drawString("P", bx - 5, BARRA_Y + 22);

            g2.setColor(new Color(160, 80, 0));
            g2.setStroke(new BasicStroke(2f));
            int setaY = BARRA_Y + altura + 22;
            g2.drawLine(bx, BARRA_Y + altura, bx, setaY);
            g2.fillPolygon(new int[]{bx - 5, bx + 5, bx}, new int[]{setaY - 5, setaY - 5, setaY}, 3);
        }

        private void desenharResultados(Graphics2D g2) {
            int rx = 500, ry = 24, rw = 250, rh = 110;
            g2.setColor(new Color(230, 245, 255, 220));
            g2.fillRoundRect(rx, ry, rw, rh, 12, 12);
            g2.setColor(new Color(80, 130, 200));
            g2.drawRoundRect(rx, ry, rw, rh, 12, 12);

            g2.setColor(new Color(30, 60, 120));
            g2.setFont(new Font("Arial", Font.BOLD, 13));
            g2.drawString("Resultado do equilíbrio", rx + 32, ry + 22);

            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            g2.setColor(new Color(40, 40, 40));
            g2.drawString(String.format("(a) x = %.4f m", resultX), rx + 16, ry + 46);
            g2.drawString(String.format("(b) h = %.4f m", resultH), rx + 16, ry + 68);

            g2.setFont(new Font("Arial", Font.ITALIC, 11));
            g2.setColor(new Color(100, 100, 100));
            g2.drawString("Apoio no centro (L/2)", rx + 16, ry + 90);
        }
    }
}
