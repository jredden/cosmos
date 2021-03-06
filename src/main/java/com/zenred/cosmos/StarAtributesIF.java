package com.zenred.cosmos;

/**
 * StarAtributesIF.java
 *
 *
 * Created: Mon Jul 22 07:06:30 2002
 *
 * @author <a href="mailto: "</a>
 * @version
 */

public interface StarAtributesIF {
    /*    */
    /* starluminosity   */
    /*    */
    
    public static final double starlum[]=
    {1.69e8, 1.21e8, 1.81e7, 1.89e6, 1.32e6,
     1.17e6, 1.07e6, 9.96e5, 8.8e5, 6.98e5,
     /*                 O super giants II */
     5.6e5, 4.78e5, 4.24e5, 3.78e5, 2.89e5,
     2.41e5, 2.01e5, 1.82e5, 1.63e5, 1.44e5,
     /*                 B super giants II */
     1.07e5, 9.56e4, 9.04e4, 8.75e4, 8.56e4,
     8.11e4, 7.64e4, 7.32e4, 7.18e4, 6.6e4,
     /*                 A super giants II */
     6.1e4, 5.87e4, 5.7e4, 5.5e4, 5.3e4,
     5.47e4, 5.62e4, 5.89e4, 6.26e4, 6.41e4,
     /*                 F super giants II */
     6.7e4, 7.1e4, 7.5e4, 7.9e4, 8.4e4,
     8.8e4, 9.1e4, 9.24e4, 9.38e4, 9.54e4,
     /*                 G super giants II  */
     9.7e4, 9.9e4, 1.01e5, 1.03e5, 1.05e5, 
     1.07e5, 1.09e5, 1.11e5, 1.13e5, 1.15e5,
     /*                 K super giants II */
     1.17e5, 1.19e5, 1.21e5, 1.24e5, 1.27e5,
     1.30e5, 1.33e5, 1.35e5, 1.37e5, 1.41e5,
     /*                 M super giants II */
     1.81e6, 1.42e6, 1.03e6, 9.8e5, 9.1e5,
     8.6e5, 7.9e5, 6.7e5, 5.3e5, 3.8e5,
     /*                 O super giants I */
     2.7e5, 2.34e5, 1.81e5, 1.22e5, 9.13e4,
     7.26e5, 4.03e4, 3.28e4, 2.76e4, 2.13e4,
     /*                 B super giants I */
     1.5e4, 1.45e4, 1.4e4, 1.3e4, 1.2e4,
     1.17e4, 1.14e4, 1.09e4, 9.8e3, 8.1e3,
     /*                 A super giants I */
     7.4e3, 6.8e3, 6.4e3, 5.9e3, 5.5e3,
     5.4e3, 5.3e3, 5.4e3, 5.5e3, 5.8e3,
     /*                 F super giants I */
     6.1e3, 6.5e3, 6.9e3, 7.4e3, 7.7e3,
     8.1e3, 9.1e3, 9.8e3, 1.01e4, 1.12e4,
     /*                 G super giants I */
     1.17e4, 1.23e4, 1.46e4, 1.65e4, 1.81e4,
     2.09e4, 2.32e4, 2.87e4, 3.16e4, 3.89e4,
     /*                 K super giants I */
     4.6e4, 5.39e4, 6.01e4, 6.95e4, 7.82e4,
     8.45e4, 9.01e4, 9.87e4, 1.08e5, 1.17e5,
     /*                 M super giants I */
     9.9e5, 8.4e5, 7.8e5, 6.9e5, 5.1e5,
     4.7e5, 3.8e5, 3.3e5, 2.9e5, 2.1e5,
     /*                 O giants II */
     1.7e5, 1.43e5, 1.09e5, 8.34e4, 4.88e4,
     2.76e4, 1.53e4, 9.78e3, 8.76e3, 5.48e3,
     /*                 B giants II */
     2.2e3, 1.92e3, 1.66e3, 1.44e3, 1.12e3,
     988.0, 750.0, 701.0, 650.0, 625.0,
     /*                 A giants II */
     600.0, 584.0, 564.0, 545.0, 531.0,
     510.0, 530.0, 538.0, 545.0, 550.0,
     /*                 F giants II */
     560.0, 622.0, 680.0, 690.0, 700.0,
     740.0, 760.0, 788.0, 820.0, 850.0,
     /*                 G giants II */
     890.0, 1.13e3, 1.49e3, 1.78e3, 2.05e3,
     2.45e3, 2.9e3, 3.3e3, 3.7e3, 4.1e3,
     /*                 K giants II */
     4.6e3, 6.1e3, 8.2e3, 9.98e3, 1.18e4,
     1.34e4, 1.52e4, 1.55e4, 1.58e4, 1.63e4,
     /*                 M giants II */
     7.1e5, 6.21e5, 5.38e5, 3.89e5, 2.11e5,
     1.86e5, 1.69e5, 1.56e5, 1.42e5, 1.28e5,
     /*                 O giants I */
     1.07e5, 5.06e4, 1.89e4, 4.23e4, 9.98e3,
     6.7e3, 3.26e3, 2.82e3, 2.61e3, 1.09e3,
     /*                 B giants I */
     280.0, 245.0, 199.0, 146.7, 110.0,
     90.0, 72.0, 68.0, 62.0, 58.0,
     /*                 A giants I */
     53.0, 51.0, 49.0, 47.0, 45.0,
     43.0, 46.0, 47.5, 49.0, 50.0,
     /*                 F giants I */
     50.2, 54.9, 60.0, 65.0, 70.0,
     75.0, 79.0, 80.7, 82.0, 90.0,
     /*                 G giants I */
     95.0, 146.0, 185.0, 231.0, 275.0,
     320.0, 350.0, 381.0, 410.0, 440.0,
     /*                 K giants I */
     470.0, 498.0, 1.38e3, 1.55e3, 1.71e3,
     2.28e3, 2.37e3, 2.44e3, 2.59e3, 2.69e3,
     /*                 M giants I */
     2.5e5, 1.26e5, 1.88e5, 1.72e5, 1.58e5,
     1.46e5, 1.35e5, 1.26e5, 1.12e5, 1.08e5,
     /*                 O sub giants */
     8.1e4, 4.5e4, 1.2e4, 8.3e3, 5.2e3,
     2.0e3, 1.12e3, 984.0, 705.0, 289.0,
     /*                 B sub giants */
     156.0, 135.0, 124.0, 103.0, 92.0,
     37.0, 33.0, 30.5, 27.0, 20.0,
     /*                 A sub giants */
     19.0, 18.0, 17.0, 16.0, 15.0,
     12.0, 11.0, 10.0, 9.0, 7.0,
     /*                 F sub giants */
     6.5, 6.24, 5.9, 5.6, 5.3,
     4.9, 4.82, 4.78, 4.73, 4.69,
     /*                 G sub giants */
     4.67, 4.61, 4.55, 4.48, 4.41,
     4.40, 4.39, 4.37, 4.35, 4.33,
     /*                 K sub giants */
     4.32, 4.315, 4.31, 4.309, 4.308,
     4.305, 4.302, 4.297, 4.293, 4.29,
     /*                 M sub giants */
     1.45e5, 1.41e5, 1.38e5, 1.27e5, 1.23e5,
     1.19e5, 1.16e5, 1.12e5, 1.09e5, 7.89e4,
     /*                 O main */
     5.6e4, 3.32e4, 1.95e4, 1.23e4, 8.68e3,
     1.410e3, 729.0, 673.0, 495.0, 281.0,
     /*                 B main */
     90.0, 75.0, 60.0, 45.0, 30.0,
     16.0, 14.6, 13.5, 12.1, 9.4,
     /*                 A main */
     8.1, 7.65, 5.62, 4.37, 3.18,
     2.76, 2.32, 1.84, 1.55, 1.52,
     /*                 F main */
     1.2, 1.1, 1.0, .945, .814,
     .712, .610, .558, .501, .408,
     /*                 G main */
     .363, .333, .282, .257, .216,
     .184, .162, .147, .123, .105,
     /*                 K main */
     .0912, .0734, .0595, .0466, .0317,
     .0276, .0152, .0134, .0111, .0096,
     /*                 M main */
     11.0, 10.5, 10.0, 9.5, 9.0,
     8.5, 8.0, 7.5, 7.0, 6.5,
     /*                 O sub dwarf */
     6.0, 5.6, 5.2, 4.8, 4.4,
     4.0, 4.6, 4.2, 3.2, 3.4,
     /*                 B sub dwarf */
     3.0, 2.8, 2.6, 2.4, 2.2,
     2.0, 1.8, 1.6, 1.4, 1.2,
     /*                 A sub dwarf */
     1.0, .96, .92, .88, .84,
     .80, .76, .72, .68, .64,
     /*                 F sub dwarf */
     .6, .5856, .5712, .5568, .5424,
     .528, .5084, .4888, .4692, .4496,
     /*                 G sub dwarf */
     .43, .41, .39, .37, .35,
     .33, .2948, .2596, .2244, .1892,
     /*                 K sub dwarf */
     .154, .144, .134, .124, .114,
     .104, .0948, .0856, .0764, .0672,
     /*                 M sub dwarf */
     .392, .3574, .3228, .2882, .2536,
     .219, .1844, .1498, .1152, .0806,
     /*                 O dwarf */
     .046, .0419, .0378, .0337, .0296,
     .0255, .0214, .0173, .0132, .0091,
     /*                 B dwarf */
     5.0e-3, 4.53e-3, 4.06e-3, 3.59e-3, 3.12e-3,
     2.65e-3, 2.18e-3, 1.71e-3, 1.24e-3, 7.7e-4,
     /*                 A dwarfs */
     3.0e-4, 2.76e-4, 2.52e-4, 2.28e-4, 2.04e-4,
     1.8e-4, 1.56e-4, 1.32e-4, 1.08e-4, 8.4e-5,
     /*                 F dwarfs */
     6.0e-5, 5.8e-5, 5.6e-5, 5.4e-5, 5.2e-5,
     5.0e-5, 4.8e-5, 4.6e-5, 4.4e-5, 4.2e-5,
     /*                 G dwarfs */
     4.0e-5, 3.95e-5, 3.9e-5, 3.85e-5, 3.8e-5,
     3.75e-5, 3.7e-5, 3.65e-5, 3.6e-5, 3.55e-5,
     /*                 K dwarfs */
     3.0e-5, 2.95e-5, 2.9e-5, 2.85e-5, 2.8e-5,
     2.75e-5, 2.7e-5, 2.65e-5, 2.6e-5, 2.55e-5};
    /*                 M dwarfs */
    
    /*    */
    /* starcolor   */
    /*    */
    public static final String BLUE_SG_II = "blue.sg.ii";
    public static final String LTBL_SG_II = "ltbl.sg.ii";
    public static final String WHIT_SG_II = "whit.sg.ii";
    public static final String PYEL_SG_II = "pyel.sg.ii";
    public static final String YELO_SG_II = "yelo.sg.ii";
    public static final String ORNG_SG_II = "orng.sg.ii";
    public static final String RED__SG_II = "red_.sg.ii";
    public static final String BLUE_SG_I = "blue.sg.i ";
    public static final String LTBL_SG_I = "ltbl.sg.i ";
    public static final String WHIT_SG_I = "whit.sg.i ";
    public static final String PYEL_SG_I = "pyel.sg.i ";
    public static final String YELO_SG_I = "yelo.sg.i ";
    public static final String ORNG_SG_I = "orng.sg.i ";
    public static final String RED__SG_I = "red_.sg.i ";
    public static final String BLUE_GI_II = "blue.gi.ii";
    public static final String LTBL_GI_II = "ltbl.gi.ii";
    public static final String WHIT_GI_II = "whit.gi.ii";
    public static final String PYEL_GI_II = "pyel.gi.ii";
    public static final String YELO_GI_II = "yelo.gi.ii";
    public static final String ORNG_GI_II = "orng.gi.ii";
    public static final String RED__GI_II = "red_.gi.ii";
    public static final String BLUE_GI_I = "blue.gi.i ";
    public static final String LTBL_GI_I = "ltbl.gi.i ";
    public static final String WHIT_GI_I = "whit.gi.i ";
    public static final String PYEL_GI_I = "pyel.gi.i ";
    public static final String YELO_GI_I = "yelo.gi.i ";
    public static final String ORNG_GI_I = "orng.gi.i ";
    public static final String RED__GI_I = "red_.gi.i ";
    public static final String BLUE_SUBGI = "blue.subgi";
    public static final String LTBL_SUBGI = "ltbl.subgi";
    public static final String WHIT_SUBGI = "whit.subgi";
    public static final String PYEL_SUBGI = "pyel.subgi";
    public static final String YELO_SUBGI = "yelo.subgi";
    public static final String ORNG_SUBGI = "orng.subgi";
    public static final String RED__SUBGI = "red_.subgi";
    public static final String BLUE_MAINS = "blue.mains";
    public static final String LTBL_MAINS = "ltbl.mains";
    public static final String WHIT_MAINS = "whit.mains";
    public static final String PYEL_MAINS = "pyel.mains";
    public static final String YELO_MAINS = "yelo.mains";
    public static final String ORNG_MAINS = "orng.mains";
    public static final String RED__MAINS = "red_.mains";
    public static final String BLUE_SUBDW = "blue.subdw";
    public static final String LTBL_SUBDW = "ltbl.subdw";
    public static final String WHIT_SUBDW = "whit.subdw";
    public static final String PYEL_SUBDW = "pyel.subdw";
    public static final String YELO_SUBDW = "yelo.subdw";
    public static final String ORNG_SUBDW = "orng.subdw";
    public static final String RED__SUBDW = "red_.subdw";
    public static final String BLUE_DWARF = "blue.dwarf";
    public static final String LTBL_DWARF = "ltbl.dwarf";
    public static final String WHIT_DWARF = "whit.dwarf";
    public static final String PYEL_DWARF = "pyel.dwarf";
    public static final String YELO_DWARF = "yelo.dwarf";
    public static final String ORNG_DWARF = "orng.dwarf";
    public static final String RED__DWARF = "red_.dwarf";
    public static final String PURPLE_RED = "purple_red";
    public static final String BROWN_SUBS = "brown_subs";  
    public static final String DRKBRN_SDW = "drkbrn_sdw";
                  
    public static final String [] starcolor={
    	BLUE_SG_II,
    	LTBL_SG_II,
    	WHIT_SG_II,
    	PYEL_SG_II,
    	YELO_SG_II,
    	ORNG_SG_II,
    	RED__SG_II,
    	BLUE_SG_I,
    	LTBL_SG_I,
    	WHIT_SG_I,
    	PYEL_SG_I,
    	YELO_SG_I,
    	ORNG_SG_I,
    	RED__SG_I,
    	BLUE_GI_II,
    	LTBL_GI_II,
    	WHIT_GI_II,
    	PYEL_GI_II,
    	YELO_GI_II,
    	ORNG_GI_II,
    	RED__GI_II,
    	BLUE_GI_I,
    	LTBL_GI_I,
    	WHIT_GI_I,
    	PYEL_GI_I,
    	YELO_GI_I,
    	ORNG_GI_I,
    	RED__GI_I,
    	BLUE_SUBGI,
    	LTBL_SUBGI,
    	WHIT_SUBGI,
    	PYEL_SUBGI,
    	YELO_SUBGI,
    	ORNG_SUBGI,
    	RED__SUBGI,
    	BLUE_MAINS,
    	LTBL_MAINS,
    	WHIT_MAINS,
    	PYEL_MAINS,
    	YELO_MAINS,
    	ORNG_MAINS,
    	RED__MAINS,
    	BLUE_SUBDW,
    	LTBL_SUBDW,
    	WHIT_SUBDW,
    	PYEL_SUBDW,
    	YELO_SUBDW,
    	ORNG_SUBDW,
    	RED__SUBDW,
    	BLUE_DWARF,
    	LTBL_DWARF,
    	WHIT_DWARF,
    	PYEL_DWARF,
    	YELO_DWARF,
    	ORNG_DWARF,
    	RED__DWARF,
    	PURPLE_RED,
    	BROWN_SUBS,
    	DRKBRN_SDW};
    
    /*    */
    /* masterstartypeindex   */
    /*    */
    
    public static final int [] stardex =
    {1,3,5,7,9,11,13,15,17,19,
     /*  o supergiants II */
     21,23,25,27,29,31,33,35,37,39,
     /*  b supergiants II */
     41,42,43,44,45,46,47,48,49,50,
     /*  a supergiants II */
     51,52,53,54,55,56,57,58,59,60,
     /*  f supergiants II */
     61,62,63,64,65,66,67,68,69,70,
     /*  g supergiants II */
     71,72,73,74,75,76,77,78,79,80,
     /*  k supergiants II */
     81,83,85,87,89,91,93,95,97,99,
     /*  m supergiants II */
     101,103,105,107,109,111,113,115,117,119,
     /*  o supergiants I */
     121,123,125,127,129,131,133,135,137,139,
     /*  b supergiants I */
     141,142,143,144,145,146,147,148,149,150,
     /*  a supergiants I */
     151,152,153,154,155,156,157,158,159,160,
     /*  f supergiants I */
     161,162,163,164,165,166,167,168,169,170,
     /*  g supergiants I */
     171,172,173,174,175,176,177,178,179,180,
     /*  k supergiants I */
     181,183,185,187,189,191,193,195,197,199,
     /*  m supergiants I */
     201,203,205,207,209,211,213,215,217,219,
     /*  o giants II */
     211,213,215,217,219,221,223,225,227,229,
     /*  b giants II */
     231,233,235,237,239,241,243,245,247,249,
     /*  a giants II */
     251,252,253,254,255,256,257,258,259,260,
     /*  f giants II */
     261,262,263,264,265,266,267,268,269,270,
     /*  g giants II */
     271,272,273,274,275,276,277,278,279,280,
     /*  k giants II */
     281,283,285,287,289,291,293,295,297,299,
     /*  m giants II */
     301,303,305,307,309,311,313,315,317,319,
     /*  o giants I */
     321,324,327,330,333,336,339,342,345,348,
     /*  b giants I */
     351,353,355,357,359,361,363,365,367,369,
     /*  a giants I */
     371,372,373,374,375,376,377,378,379,380,
     /*  f giants I */
     381,382,383,384,385,386,387,388,389,399,
     /*  g giants I */
     400,403,406,409,412,415,418,421,424,427,
     /*  k giants I */
     430,434,438,442,446,450,454,458,462,466,
     /*  m giants I */
     470,472,474,476,478,490,492,494,498,500,
     /*  o subgiants */
     502,504,506,508,510,512,514,516,518,520,
     /*  b subgiants */
     522,523,524,525,526,527,528,529,530,531,
     /*  a subgiants */
     532,533,534,535,536,537,538,539,540,541,
     /*  f subgiants */
     542,543,544,545,546,547,548,549,550,551,
     /*  g subgiants */
     552,554,556,558,560,562,564,566,568,570,
     /*  k subgiants */
     572,575,578,581,584,587,590,593,596,599,
     /*  m subgiants */
     602,603,604,605,606,607,608,609,610,611,
     /*  o main */
     612,614,616,618,620,622,624,626,628,630,
     /*  b main */
     632,642,652,662,672,682,692,702,712,722,
     /*  a main */
     732,772,812,852,892,932,972,1002,1042,1082,
     /*  f main */
     1122,1202,1282,1362,1442,1522,1602,1682,1462,1542,
     /*  g main */
     1622,1722,1822,1922,2022,2122,2222,2322,2422,2522,
     /*  k main */
     2622,2742,2862,2982,3102,3222,3342,3462,3572,3682,
     /*  m main */
     3800,3801,3802,3803,3804,3805,3806,3807,3808,3809,
     /*  o subdwarf */
     3810,3811,3812,3813,3814,3815,3816,3817,3818,3819,
     /*  b subdwarf */
     3820,3821,3822,3823,3824,3825,3826,3827,3828,3829,
     /*  a subdwarf */
     3830,3831,3832,3833,3834,3835,3836,3837,3838,3839,
     /*  f subdwarf */
     3840,3841,3842,3843,3844,3845,3846,3847,3848,3849,
     /*  g subdwarf */
     3850,3852,3854,3856,3858,3860,3862,3864,3866,3868,
     /*  k subdwarf */
     3870,3873,3876,3879,3882,3885,3888,3891,3894,3897,
     /*  m subdwarf */
     3900,3901,3902,3903,3904,3905,3906,3907,3908,3909,
     /*  o dwarf */
     3910,3911,3912,3913,3914,3915,3916,3917,3918,3919,
     /*  b dwarf */
     3920,3921,3922,3923,3924,3925,3926,3927,3928,3929,
     /*  a dwarf */
     3930,3931,3932,3933,3934,3935,3936,3937,3938,3939,
     /*  f dwarf */
     3940,3941,3942,3943,3944,3945,3946,3947,3948,3949,
     /*  g dwarf */
     3950,3960,3970,3980,3990,4000,4010,4020,4030,4040,
     /*  k dwarf */
     4050,4100,4150,4200,4250,4300,4350,4400,4450,4500,
     /*  m dwarf */
     9999,
     /* purple red sub dwarf */
     99500,
     /* brown sub star */
     99999};
    /* dark brown sub star*/
    /*
      starmassbystartype
    */ 
    
    public static final double [] starmass = {160.0,12.0,30.0,
                                        /*  sg.ii */
                                        50.0,10.0,25.0,
                                        /*  sg.i */
                                        20.0,8.1,18.0,
                                        /*  gi.ii */
                                        25.0,2.5,9.2,
                                        /*  gi.i */
                                        20.0,1.75,5.0,
                                        /*  subgi */
                                        18.0,1.04,0.215,
                                        /*   main  */
                                        1.8,0.6,0.058,
                                        /*  subdw */
                                        0.26,0.63,1.11};
    /*   dwarf */
    
    public static final String [] starseq = 
    {"o","b","a","f","g","k","m"};
    public static final String [] stardwarf =
    {"do","db","da","df","dg","dk","dm"};
    public static final double qtrau=3.7375e7;
    public static final double halfpsec=5.952e12;
    public static final double convau=1.495e8;
    public static final String cdot =".";
    public static final int SUBDWARF = 490;
    public static final int COLUMNMODULUS = 10;
    
    
}// StarAtributesIF
