# Fisica-Trabalho
<img width="808" height="622" alt="image" src="https://github.com/user-attachments/assets/4c0580de-47a9-4e46-b8b7-6190c9eccc1a" />
Equilíbrio da Barra — Problema 21.32

Aplicação em Java Swing que resolve e visualiza o Problema 21.32 (equilíbrio
eletrostático de uma barra articulada), permitindo calcular os valores pedidos
no enunciado e exibir uma ilustração do resultado final.


📋 Descrição do exercício


A Fig. 21.32 mostra uma barra longa, isolante, de massa desprezível e
comprimento L, articulada no centro e equilibrada por um bloco de peso
P situado a uma distância x da extremidade esquerda. Nas extremidades
direita e esquerda da barra existem pequenas esferas condutoras, de carga
positiva q e 2q, respectivamente. A uma distância vertical h
abaixo das esferas existem esferas fixas de carga positiva Q.

(a) Determine a distância x para que a barra fique equilibrada na
horizontal.

(b) Qual deve ser o valor de h para que a barra não exerça força
vertical sobre o apoio quando está equilibrada na horizontal?



A barra está apoiada (articulada) bem no centro. As duas esferas nas pontas da
barra (+q à esquerda e +2q à direita) são repelidas verticalmente para
cima pelas cargas fixas +Q posicionadas logo abaixo de cada uma, a uma
distância h. O peso P, pendurado em algum ponto da barra, é o que mantém
o sistema em equilíbrio.


🧮 Resolução

(a) Cálculo de x

Pela Lei de Coulomb, a força elétrica (repulsiva, vertical para cima) entre
cada esfera da barra e a carga fixa correspondente, separadas por uma
distância h, é:

F_q  = k·q·Q  / h²      (esfera esquerda, carga q)
F_2q = k·2q·Q / h²      (esfera direita, carga 2q)

onde k = 8,99 × 10⁹ N·m²/C² é a constante eletrostática.

Como F_2q é o dobro de F_q, a extremidade direita da barra é empurrada
para cima com força maior que a esquerda. Para a barra permanecer em
equilíbrio (sem girar em torno do apoio), aplicamos a soma dos torques em
relação ao apoio igual a zero:

F_q · (L/2) + P · (x - L/2) = F_2q · (L/2)

Isolando x:

x = L/2 + (F_2q - F_q) · (L/2) / P

Ou seja, o bloco precisa ficar à direita do centro (x > L/2), compensando
o torque extra causado pela carga 2q.

(b) Cálculo de h

A barra não exerce força vertical sobre o apoio quando a soma das duas
forças elétricas equilibra sozinha todo o peso P (o apoio fica "de
folga", sem precisar empurrar nem segurar a barra):

F_q + F_2q = P
k·q·Q/h² + 2k·q·Q/h² = P
3k·q·Q / h² = P

Isolando h:

h = √(3·k·q·Q / P)


💻 Estrutura do código

ArquivoResponsabilidadeMain.javaInterface gráfica principal: campos de entrada, botões, leitura/validação dos dados e exibição dos resultados.Calcular.javaLógica matemática pura: implementa as fórmulas de x (item a) e h (item b) descritas acima.Animacao.javaJanela secundária que desenha uma ilustração da barra já equilibrada, com os valores calculados (posição do bloco, esferas, apoio).


▶️ Como executar

bashjavac Main.java Calcular.java Animacao.java
java Main


A janela principal abre com valores de exemplo já preenchidos em cada campo
(L, P, Q, q, h).
Edite os valores conforme desejar e clique em Calcular.
Os resultados de x (item a) e h (item b) aparecem na tela, em notação
científica.
Clique em Ver animação para abrir a janela com a ilustração do
resultado final.



⚠️ Tratamento de erros

A aplicação valida as entradas em duas camadas:


Valores não numéricos (ex.: campo vazio ou com letras): capturado por
NumberFormatException ao tentar converter o texto digitado em número,
exibindo a mensagem "Insira valores numéricos válidos (ex: 1.0 ou
1e-6)".
Valores inválidos fisicamente (zero ou negativos): verificado
explicitamente antes do cálculo, exibindo "Todos os valores devem ser
positivos", já que comprimento, peso e cargas negativas não fazem sentido
físico neste problema.
Erros ao abrir a animação: qualquer exceção nessa etapa é capturada e
exibida numa caixa de diálogo, evitando que o programa feche
inesperadamente.


Em todos os casos, o programa permanece aberto e utilizável após o erro —
nenhuma exceção derruba a aplicação.


🖼️ Capturas de tela

<img width="982" height="562" alt="image" src="https://github.com/user-attachments/assets/248bab10-fdbd-4d03-ac94-17f62a5b7909" />

<img width="503" height="362" alt="image" src="https://github.com/user-attachments/assets/eec6f545-7ae8-4f20-84ea-a58a56c52291" />


📚 Referência

Problema 21.32 — Halliday, Resnick & Walker, Fundamentos de Física, Vol. 3
(Eletromagnetismo).
