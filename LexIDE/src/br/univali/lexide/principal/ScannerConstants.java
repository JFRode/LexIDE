package br.univali.lexide.principal;

import br.univali.lexide.importador.Importador;

public interface ScannerConstants
{
    int[] SCANNER_TABLE_INDEXES = 
    {
        0,
        92,
        96,
        97,
        288,
        288,
        289,
        480,
        480,
        480,
        480,
        480,
        480,
        480,
        490,
        492,
        505,
        516,
        516,
        516,
        518,
        519,
        521,
        584,
        584,
        584,
        584,
        647,
        710,
        773,
        836,
        899,
        962,
        1025,
        1088,
        1151,
        1214,
        1277,
        1340,
        1403,
        1466,
        1466,
        1467,
        1467,
        1467,
        1467,
        1658,
        1658,
        1659,
        1669,
        1766,
        1957,
        1967,
        1969,
        1985,
        1985,
        1985,
        1985,
        1985,
        1985,
        2048,
        2111,
        2174,
        2237,
        2300,
        2363,
        2426,
        2489,
        2552,
        2615,
        2678,
        2741,
        2804,
        2867,
        2930,
        2993,
        3056,
        3119,
        3119,
        3119,
        3216,
        3407,
        3409,
        3425,
        3488,
        3551,
        3614,
        3677,
        3740,
        3803,
        3866,
        3929,
        3992,
        4055,
        4118,
        4181,
        4244,
        4307,
        4404,
        4467,
        4530,
        4593,
        4656,
        4719,
        4783,
        4846,
        4909,
        4972,
        5035,
        5098,
        5161,
        5224,
        5287,
        5288,
        5351,
        5414,
        5477,
        5540,
        5603,
        5603,
        5666,
        5729
    };

    int[][] SCANNER_TABLE = new Importador().getTabela();

    int[] TOKEN_STATE = {  0,   0,  34,  -1,  24,  37,  -1,  51,  52,  22,  20,  44,  21,  43,  23,   2,   2,  45,  46,  28,  25,  27,  56,  47,  48,  40,  56,  56,  56,  56,  56,  56,  56,  56,  56,  56,  56,  56,  56,  56,  49,  38,  50,  39,  31,   4,  35,  -1,   3,  -1,  57,  -1,  -1,  -1,  41,  30,  26,  29,  42,  56,  56,  56,  56,  56,  18,  56,  56,  56,  15,  56,  56,  33,  56,  56,  56,  56,  56,  36,   5,  -1,  57,   7,   6,  32,  56,  56,  56,  56,  56,  19,   8,  56,  56,  56,  56,  56,  56,  58,  56,  12,  56,  16,  56,  56,  53,  56,  14,  56,  56,  56,  56,   9,  -1,  56,  17,  54,  56,  10,  55,  11,  13 };

    String[] SCANNER_ERROR =
    {
        "Caractere nao esperado",
        "",
        "",
        "Erro identificando valor_string",
        "",
        "",
        "Erro identificando valor_char",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando valor_char",
        "",
        "Erro identificando comentario_multipla",
        "",
        "Erro identificando valor_float",
        "Erro identificando valor_bin",
        "Erro identificando valor_hexa",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando comentario_multipla",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "Erro identificando main",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    };

}
