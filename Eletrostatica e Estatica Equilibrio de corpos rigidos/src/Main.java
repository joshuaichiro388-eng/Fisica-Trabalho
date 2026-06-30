import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Equilíbrio da Barra - Problema 21.32");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JPanel fields = new JPanel(new GridLayout(6, 2, 6, 6));
            fields.setBorder(BorderFactory.createEmptyBorder(14, 14, 8, 14));

            JTextField tfL = new JTextField("");
            JTextField tfP = new JTextField("");
            JTextField tfQ = new JTextField("1e-6");
            JTextField tfq = new JTextField("1e-6");
            JTextField tfh = new JTextField("");
            JTextField tfx = new JTextField("");

            fields.add(new JLabel("Comprimento da barra L (m):")); fields.add(tfL);
            fields.add(new JLabel("Peso do bloco P (N):"));        fields.add(tfP);
            fields.add(new JLabel("Carga fixa Q (C):"));           fields.add(tfQ);
            fields.add(new JLabel("Carga da esfera q (C):"));      fields.add(tfq);
            fields.add(new JLabel("Distância vertical h (m):"));   fields.add(tfh);
            fields.add(new JLabel("Posição x atual (x):"));        fields.add(tfx);

            JLabel resultLabel = new JLabel("Preencha os campos e clique em Calcular.");
            resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
            resultLabel.setForeground(new Color(30, 100, 180));
            resultLabel.setFont(new Font("Arial", Font.BOLD, 12));
            resultLabel.setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 0));

            JButton calcBtn = new JButton("Calcular");
            JButton animBtn = new JButton("Ver imagem");
            animBtn.setEnabled(false);

            JPanel botoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
            botoes.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            botoes.add(calcBtn);
            botoes.add(animBtn);

            JPanel sul = new JPanel(new BorderLayout());
            sul.add(resultLabel, BorderLayout.CENTER);
            sul.add(botoes,      BorderLayout.SOUTH);

            frame.setLayout(new BorderLayout());
            frame.add(fields, BorderLayout.CENTER);
            frame.add(sul,    BorderLayout.SOUTH);

            frame.pack();
            frame.setMinimumSize(new Dimension(420, 300));
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            final double[] cache = {0.0, 0.0};

            calcBtn.addActionListener(e -> {
                try {
                    double L = Double.parseDouble(tfL.getText().trim());
                    double P = Double.parseDouble(tfP.getText().trim());
                    double Q = Double.parseDouble(tfQ.getText().trim());
                    double q = Double.parseDouble(tfq.getText().trim());
                    double h = Double.parseDouble(tfh.getText().trim());

                    if (L <= 0 || P <= 0 || Q <= 0 || q <= 0 || h <= 0) {
                        JOptionPane.showMessageDialog(frame, "Todos os valores devem ser positivos.",
                                "Entrada inválida", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    Calcular calc = new Calcular(L, P, Q, q, h);
                    cache[0] = calc.calcularX();
                    cache[1] = calc.calcularH();

                    tfx.setText(String.format(Locale.US, "%.6f", cache[0]));

                    resultLabel.setText(String.format(
                            "<html>(a) x = <b>%.4e m</b> &nbsp;&nbsp;&nbsp; (b) h = <b>%.4e m</b></html>",
                            cache[0], cache[1]));
                    animBtn.setEnabled(true);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Insira valores numéricos válidos (ex: 1.0 ou 1e-6).",
                            "Erro de formato", JOptionPane.ERROR_MESSAGE);
                }
            });

            animBtn.addActionListener(e -> {
                try {
                    double L = Double.parseDouble(tfL.getText().trim());
                    double P = Double.parseDouble(tfP.getText().trim());
                    double Q = Double.parseDouble(tfQ.getText().trim());
                    double q = Double.parseDouble(tfq.getText().trim());
                    double h = Double.parseDouble(tfh.getText().trim());
                    double x = Double.parseDouble(tfx.getText().trim());

                    new Animacao(L, P, Q, q, x, h, cache[0], cache[1]);

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame,
                            "Erro ao abrir animação: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            });
        });
    }
}
