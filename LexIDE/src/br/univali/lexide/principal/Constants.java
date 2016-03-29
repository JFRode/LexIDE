package br.univali.lexide.principal;
public interface Constants extends ScannerConstants, ParserConstants
{
    int EPSILON  = 0;
    int DOLLAR   = 1;

    int t_valor_int = 2;
    int t_valor_float = 3;
    int t_valor_string = 4;
    int t_valor_char = 5;
    int t_valor_hexa = 6;
    int t_valor_bin = 7;
    int t_int = 8;
    int t_float = 9;
    int t_double = 10;
    int t_string = 11;
    int t_char = 12;
    int t_boolean = 13;
    int t_void = 14;
    int t_if = 15;
    int t_else = 16;
    int t_while = 17;
    int t_for = 18;
    int t_op_mais = 19;
    int t_op_menos = 20;
    int t_igual = 21;
    int t_igual_igual = 22;
    int t_maior = 23;
    int t_menor = 24;
    int t_maior_igual = 25;
    int t_menor_igual = 26;
    int t_diferente = 27;
    int t_and = 28;
    int t_or = 29;
    int t_negado = 30;
    int t_and_bit = 31;
    int t_or_bit = 32;
    int t_not_bit = 33;
    int t_xor_bit = 34;
    int t_shiftl_bit = 35;
    int t_shiftr_bit = 36;
    int t_ponto = 37;
    int t_virgula = 38;
    int t_dois_pontos = 39;
    int t_ponto_virgula = 40;
    int t_col_abre = 41;
    int t_col_fecha = 42;
    int t_cha_abre = 43;
    int t_cha_fecha = 44;
    int t_p_abre = 45;
    int t_p_fecha = 46;
    int t_main = 47;
    int t_variavel = 48;
    int t_comentario = 49;
    int t_comentario_multipla = 50;

}
