create or replace PACKAGE BODY RCL_IGM_TEST_INFO 
IS 
  PROCEDURE RCL_IGM_GET_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
                             P_I_V_POD          VARCHAR2, 
                             P_I_V_BL           VARCHAR2 DEFAULT NULL, 
                             P_I_V_SERVICE      VARCHAR2 DEFAULT NULL, 
                             P_I_V_VESSEL       VARCHAR2 DEFAULT NULL, 
                             P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
                             P_I_V_POD_TERMINAL VARCHAR2 DEFAULT NULL, 
                             P_I_V_FROM_DATE    VARCHAR2 DEFAULT NULL, 
                             P_I_V_TO_DATE      VARCHAR2 DEFAULT NULL, 
                             P_I_V_BL_STATUS    VARCHAR2 DEFAULT NULL, 
                             P_I_V_POL          VARCHAR2 DEFAULT NULL, 
                             P_I_V_POL_TERMINAL VARCHAR2 DEFAULT NULL, 
                             P_I_V_DIRECTION    VARCHAR2 DEFAULT NULL, 
                             P_O_V_ERROR        OUT VARCHAR2) 
  IS 
    BL_COUNT NUMBER(20); 
  BEGIN 
      P_O_V_ERROR := '000000'; 


      BL_COUNT := 0; 

      SELECT COUNT(*) 
      INTO   BL_COUNT 
      FROM   RCL_IGM_DETAILS rid 
      WHERE  rid.PORT = P_I_V_POD 
             AND ( P_I_V_SERVICE IS NULL 
                    OR rid.SERVICE = P_I_V_SERVICE ) 
             AND ( P_I_V_VESSEL IS NULL 
                    OR rid.VESSEL = P_I_V_VESSEL ) 
             AND ( P_I_V_VOYAGE IS NULL 
                    OR rid.VOYAGE = P_I_V_VOYAGE ) 
             AND ( P_I_V_POD_TERMINAL IS NULL 
                    OR rid.TERMINAL = P_I_V_POD_TERMINAL ) 
             AND ( P_I_V_FROM_DATE IS NULL 
                    OR rid.BL_DATE >= P_I_V_FROM_DATE ) 
             AND ( P_I_V_TO_DATE IS NULL 
                    OR rid.BL_DATE <= P_I_V_TO_DATE ) 
             AND ( P_I_V_POL IS NULL 
                    OR rid.POL = P_I_V_POL ) 
             AND ( P_I_V_BL_STATUS IS NULL 
                    OR rid.BL_STATUS = P_I_V_BL_STATUS ) 
             AND ( P_I_V_POL_TERMINAL IS NULL 
                    OR rid.POL_TERMINAL = P_I_V_POL_TERMINAL ); 

      IF BL_COUNT = 0 THEN 
        DBMS_OUTPUT.PUT_LINE('FRESH SEARCH..!!'); 

        OPEN P_O_REFIGMTABFIND FOR 
          SELECT SYBLNO                              BL_NO, 
                 IDP10.AYIMST                        BL_STATUS, 
                 IDP10.AYISDT                        BL_DATE, 
                 ACT_SERVICE_CODE                    SERVICE, 
                 ACT_VESSEL_CODE                     VESSEL, 
                 ACT_VOYAGE_NUMBER                   VOYAGE, 
                 ACT_PORT_DIRECTION                  DIRECTION, 
                 LOAD_PORT                           POL, 
                 FROM_TERMINAL                       POL_TERMINAL, 
                 DISCHARGE_PORT                      POD, 
                 TO_TERMINAL                         POD_TERMINAL, 
                 TO_TERMINAL                         TERMINAL, 
                 (SELECT A.PARTNER_VALUE 
                  FROM   EDI_TRANSLATION_DETAIL A 
                  WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                                       FROM   EDI_TRANSLATION_HEADER ETH 
                                       WHERE  ETH.STANDARD = 'EDIFACT' 
                                              AND ETH.CODE_SET = 'IGMPORT') 
                         AND A.SEALINER_VALUE = P_I_V_POD 
                         AND ROWNUM < 2)             CUST_CODE, 
                 (SELECT A.VSCLSN 
                  FROM   ITP060 A 
                  WHERE  A.VSVESS = P_I_V_VESSEL 
                         AND ROWNUM < 2)             CALL_SIGN, 
                 (SELECT LOYD_NO 
                  FROM   ITP060 A 
                  WHERE  A.VSVESS = P_I_V_VESSEL 
                         AND ROWNUM < 2)             IMO_CODE, 
                 (SELECT PARAM_VALUE 
                  FROM   EDI_CONFIG_PARAMETER 
                  WHERE  PARAM_NAME = 'BOM_IGM_PAN') AGENT_CODE, 
                 (SELECT A.PARTNER_VALUE 
                  FROM   EDI_TRANSLATION_DETAIL A 
                  WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                                       FROM   EDI_TRANSLATION_HEADER ETH 
                                       WHERE  ETH.STANDARD = 'EDIFACT' 
                                              AND ETH.CODE_SET = 'IGMLINECD') 
                         AND A.SEALINER_VALUE = P_I_V_POD_TERMINAL 
                         AND ROWNUM < 2)             LINE_CODE, 
                 (SELECT VVPCAL 
                  FROM   (SELECT VVPCAL 
                          FROM   SEALINER.ITP063 
                          WHERE  VVVESS = P_I_V_VESSEL 
                                 AND VVVERS = 99 
                                 AND ( VVARDT 
                                       ||NVL(LPAD(VVARTM, 4, 0), '0000') ) <= 
                                     (SELECT 
                                     VVARDT 
                                     ||NVL( 
                                       LPAD(VVARTM, 4, 0), 
                                                 '0000') 
                                     AS 
                                     ETADATE 
                                     FROM 
                                     SEALINER.ITP063 
                                     WHERE 
                                     VVSRVC = P_I_V_SERVICE 
                                     AND VVPCAL = P_I_V_POD 
                                     AND VVVESS = P_I_V_VESSEL 
                                     AND VVVOYN = P_I_V_VOYAGE 
                                     AND ( 
                                         P_I_V_POD_TERMINAL IS 
                                         NULL 
                                          OR VVTRM1 = P_I_V_POD_TERMINAL ) 
                                                 AND VVVERS = 99 
                                                 AND OMMISSION_FLAG IS NULL 
                                                 AND VVFORL IS NOT NULL) 
                                 AND OMMISSION_FLAG IS NULL 
                                 AND VVFORL IS NOT NULL 
                                 AND ( VVPCAL, VVTRM1 ) NOT IN 
                                     (SELECT VVPCAL, 
                                             VVTRM1 
                                      FROM   SEALINER.ITP063 
                                      WHERE  VVSRVC = P_I_V_SERVICE 
                                             AND VVPCAL = P_I_V_POD 
                                             AND VVVESS = P_I_V_VESSEL 
                                             AND VVVOYN = P_I_V_VOYAGE 
                                             AND ( 
                                     P_I_V_POD_TERMINAL IS NULL 
                                      OR VVTRM1 = P_I_V_POD_TERMINAL ) 
                                             AND VVVERS = 99 
                                             AND OMMISSION_FLAG IS NULL 
                                             AND VVFORL IS NOT NULL) 
                          ORDER  BY VVARDT 
                                    ||NVL(LPAD(VVARTM, 4, 0), '0000') DESC, 
                                    VVPCSQ DESC) 
                  WHERE  ROWNUM = 1)                 PORT_ORIGIN, 
                 ''                                  PORT_ARRIVAL, 
                 ''                                  LAST_PORT_1, 
                 ''                                  LAST_PORT_2, 
                 ''                                  LAST_PORT_3, 
                 'Containerised'                     VESSEL_TYPE, 
                 'containers'                        GEN_DESC, 
                 ''                                  MASTER_NAME, 
                 (SELECT VSCNCD 
                  FROM   ITP060 A 
                  WHERE  A.VSVESS = P_I_V_VESSEL 
                         AND ROWNUM < 2)             VESSEL_NATION, 
                 ''                                  IGM_NUMBER, 
                 ''                                  IGM_DATE, 
                 (SELECT VVAPDT 
                  FROM   ITP063 A 
                  WHERE  VVSRVC = P_I_V_SERVICE 
                         AND VVPCAL = P_I_V_POD 
                         AND VVVESS = P_I_V_VESSEL 
                         AND VVVOYN = P_I_V_VOYAGE 
                         AND ( P_I_V_POD_TERMINAL IS NULL 
                                OR VVTRM1 = P_I_V_POD_TERMINAL ) 
                         AND A.VVVERS = 99 
                         AND A.VVFORL IS NOT NULL 
                         AND A.OMMISSION_FLAG IS NULL 
                         AND ROWNUM < 2)             ARRIVAL_DATE, 
                 (SELECT VVAPTM 
                  FROM   ITP063 A 
                  WHERE  VVSRVC = P_I_V_SERVICE 
                         AND VVPCAL = P_I_V_POD 
                         AND VVVESS = P_I_V_VESSEL 
                         AND VVVOYN = P_I_V_VOYAGE 
                         AND ( P_I_V_POD_TERMINAL IS NULL 
                                OR VVTRM1 = P_I_V_POD_TERMINAL ) 
                         AND A.VVVERS = 99 
                         AND A.VVFORL IS NOT NULL 
                         AND A.OMMISSION_FLAG IS NULL 
                         AND ROWNUM < 2)             ARRIVAL_TIME, 
                 (SELECT GROSS_TON_INTER 
                  FROM   ITP060 A 
                  WHERE  A.VSVESS = P_I_V_VESSEL 
                         AND ROWNUM < 2)             GROSS_WEIGHT, 
                 (SELECT NET_TON_INTER 
                  FROM   ITP060 A 
                  WHERE  A.VSVESS = IDP5.ACT_VESSEL_CODE 
                         AND ROWNUM < 2)             NET_WEIGHT, 
                 '0'                                 TOTAL_BLS, 
                 ''                                  LIGHT_DUE, 
                 'N'                                 SM_BT_CARGO, 
                 'N'                                 SHIP_STR_DECL, 
                 'N'                                 CREW_LST_DECL, 
                 'N'                                 CARGO_DECL, 
                 'N'                                 PASSNGR_LIST, 
                 'N'                                 CREW_EFFECT, 
                 'N'                                 MARITIME_DECL, 
                 ''                                  ITEM_NUMBER, 
                 'C'                                 CARGO_NATURE, 
                 CASE 
                   WHEN AYMPOD = AYDEST THEN 'LC' 
                   ELSE 'TI' 
                 END                                 CARGO_MOVMNT, 
                 'OT'                                ITEM_TYPE, 
                 'FCL'                               CARGO_MOVMNT_TYPE, 
                 (SELECT BT.TRANSPORT_MODE FROM IDP005 BT WHERE BT.SYBLNO=IDP5.SYBLNO AND BT.VOYAGE_SEQ<>1 AND BT.TRANSPORT_MODE IN ('T','R') AND ROWNUM <2) TRANSPORT_MODE, 
                 ''                                  ROAD_CARR_CODE, 
                 ''                                  ROAD_TP_BOND_NO, 
                 ''                                  SUBMIT_DATE_TIME, 
                 ''                                  NHAVA_SHEVA_ETA, 
                 FINAL_PLACE_OF_DELIVERY_NAME        FINAL_PLACE_DELIVERY, 
                 ''                                  PACKAGES, 
                 (SELECT A.PARTNER_VALUE 
                  FROM   EDI_TRANSLATION_DETAIL A 
                  WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                                       FROM   EDI_TRANSLATION_HEADER ETH 
                                       WHERE  ETH.STANDARD = 'EDIFACT' 
                                              AND ETH.CODE_SET = 'IGMPORT') 
                         AND A.SEALINER_VALUE = P_I_V_POD 
                         AND ROWNUM < 2)             CFS_NAME, 
                 ''                                  MBL_NO, 
                 ''                                  HBL_NO, 
                 ''                                  FROM_ITEM_NO, 
                 ''                                  TO_ITEM_NO 
          FROM   IDP005 IDP5 
                 INNER JOIN IDP010 IDP10 
                         ON IDP5.SYBLNO = IDP10.AYBLNO 
          WHERE  IDP5.DISCHARGE_PORT = P_I_V_POD 
                 AND ( P_I_V_SERVICE IS NULL 
                        OR IDP5.ACT_SERVICE_CODE = P_I_V_SERVICE ) 
                 AND ( P_I_V_VESSEL IS NULL 
                        OR IDP5.ACT_VESSEL_CODE = P_I_V_VESSEL ) 
                 AND ( P_I_V_VOYAGE IS NULL 
                        OR IDP5.ACT_VOYAGE_NUMBER = P_I_V_VOYAGE ) 
                 AND ( P_I_V_POD_TERMINAL IS NULL 
                        OR IDP5.TO_TERMINAL = P_I_V_POD_TERMINAL ) 
                 AND ( P_I_V_FROM_DATE IS NULL 
                        OR IDP10.AYISDT >= P_I_V_FROM_DATE ) 
                 AND ( P_I_V_TO_DATE IS NULL 
                        OR IDP10.AYISDT <= P_I_V_TO_DATE ) 
                 AND ( P_I_V_BL_STATUS IS NULL 
                        OR IDP10.AYIMST = P_I_V_BL_STATUS ) 
                 AND ( P_I_V_POL IS NULL 
                        OR IDP5.LOAD_PORT = P_I_V_POL ) 
                 AND ( P_I_V_POL_TERMINAL IS NULL 
                        OR IDP5.FROM_TERMINAL = P_I_V_POL_TERMINAL ) 
                 AND IDP10.AYSORC = 'C' 
          --AND IDP5.VOYAGE_SEQ<>1   
          --AND IDP5.TRANSPORT_MODE in ('T','R')  
          ; 
      ELSE 
        DBMS_OUTPUT.PUT_LINE('OLD RECORDS FOUND IN SEARCH : ' 
                             || BL_COUNT); 

        OPEN P_O_REFIGMTABFIND FOR 
          SELECT NS.BL_NO                BL_NO, 
                 NS.BL_STATUS            BL_STATUS, 
                 NS.BL_DATE              BL_DATE, 
                 OS.SERVICE              SERVICE, 
                 OS.VESSEL               VESSEL, 
                 OS.VOYAGE               VOYAGE, 
                 OS.DIRECTION            DIRECTION, 
                 OS.POL                  POL, 
                 OS.POL_TERMINAL         POL_TERMINAL, 
                 OS.POD                  POD, 
                 OS.POD_TERMINAL         POD_TERMINAL, 
                 OS.TERMINAL             TERMINAL, 
                 OS.CUST_CODE            CUST_CODE, 
                 OS.CALL_SIGN            CALL_SIGN, 
                 OS.IMO_CODE             IMO_CODE, 
                 OS.AGENT_CODE           AGENT_CODE, 
                 OS.LINE_CODE            LINE_CODE, 
                 OS.PORT_ORIGIN          PORT_ORIGIN, 
                 OS.PORT_ARRIVAL         PORT_ARRIVAL, 
                 OS.LAST_PORT_1          LAST_PORT_1, 
                 OS.LAST_PORT_2          LAST_PORT_2, 
                 OS.LAST_PORT_3          LAST_PORT_3, 
                 OS.VESSEL_TYPE          VESSEL_TYPE, 
                 OS.GEN_DESC             GEN_DESC, 
                 OS.MASTER_NAME          MASTER_NAME, 
                 OS.VESSEL_NATION        VESSEL_NATION, 
                 OS.IGM_NUMBER           IGM_NUMBER, 
                 OS.IGM_DATE             IGM_DATE, 
                 OS.ARRIVAL_DATE         ARRIVAL_DATE, 
                 OS.ARRIVAL_TIME         ARRIVAL_TIME, 
                 OS.GROSS_WEIGHT         GROSS_WEIGHT, 
                 OS.NET_WEIGHT           NET_WEIGHT, 
                 OS.TOTAL_BLS            TOTAL_BLS, 
                 OS.LIGHT_DUE            LIGHT_DUE, 
                 OS.SM_BT_CARGO          SM_BT_CARGO, 
                 OS.SHIP_STR_DECL        SHIP_STR_DECL, 
                 OS.CREW_LST_DECL        CREW_LST_DECL, 
                 OS.CARGO_DECL           CARGO_DECL, 
                 OS.PASSNGR_LIST         PASSNGR_LIST, 
                 OS.CREW_EFFECT          CREW_EFFECT, 
                 OS.MARITIME_DECL        MARITIME_DECL, 
                 NS.ITEM_NUMBER          ITEM_NUMBER, 
                 NS.CARGO_NATURE         CARGO_NATURE, 
                 NS.CARGO_MOVMNT         CARGO_MOVMNT, 
                 NS.ITEM_TYPE            ITEM_TYPE, 
                 NS.CARGO_MOVMNT_TYPE    CARGO_MOVMNT_TYPE, 
                 NS.TRANSPORT_MODE       TRANSPORT_MODE, 
                 NS.ROAD_CARR_CODE       ROAD_CARR_CODE, 
                 NS.ROAD_TP_BOND_NO      ROAD_TP_BOND_NO, 
                 NS.SUBMIT_DATE_TIME     SUBMIT_DATE_TIME, 
                 OS.NHAVA_SHEVA_ETA      NHAVA_SHEVA_ETA, 
                 OS.FINAL_PLACE_DELIVERY FINAL_PLACE_DELIVERY, 
                 OS.PACKAGES             PACKAGES, 
                 OS.CFS_NAME             CFS_NAME, 
                 OS.MBL_NO               MBL_NO, 
                 OS.HBL_NO               HBL_NO, 
                 OS.FROM_ITEM_NO         FROM_ITEM_NO, 
                 OS.TO_ITEM_NO           TO_ITEM_NO 
          FROM   (SELECT SYBLNO                              BL_NO, 
                         IDP10.AYIMST                        BL_STATUS, 
                         IDP10.AYISDT                        BL_DATE, 
                         ACT_SERVICE_CODE                    SERVICE, 
                         ACT_VESSEL_CODE                     VESSEL, 
                         ACT_VOYAGE_NUMBER                   VOYAGE, 
                         ACT_PORT_DIRECTION                  DIRECTION, 
                         LOAD_PORT                           POL, 
                         FROM_TERMINAL                       POL_TERMINAL, 
                         DISCHARGE_PORT                      POD, 
                         TO_TERMINAL                         POD_TERMINAL, 
                         TO_TERMINAL                         TERMINAL, 
                         (SELECT A.PARTNER_VALUE 
                          FROM   EDI_TRANSLATION_DETAIL A 
                          WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                                               FROM   EDI_TRANSLATION_HEADER ETH 
                                               WHERE  ETH.STANDARD = 'EDIFACT' 
                                                      AND ETH.CODE_SET = 
                                                          'IGMPORT' 
                                              ) 
                                 AND A.SEALINER_VALUE = P_I_V_POD 
                                 AND ROWNUM < 2)             CUST_CODE, 
                         (SELECT A.VSCLSN 
                          FROM   ITP060 A 
                          WHERE  A.VSVESS = P_I_V_VESSEL 
                                 AND ROWNUM < 2)             CALL_SIGN, 
                         (SELECT LOYD_NO 
                          FROM   ITP060 A 
                          WHERE  A.VSVESS = P_I_V_VESSEL 
                                 AND ROWNUM < 2)             IMO_CODE, 
                         (SELECT PARAM_VALUE 
                          FROM   EDI_CONFIG_PARAMETER 
                          WHERE  PARAM_NAME = 'BOM_IGM_PAN') AGENT_CODE, 
                         (SELECT A.PARTNER_VALUE 
                          FROM   EDI_TRANSLATION_DETAIL A 
                          WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                                               FROM   EDI_TRANSLATION_HEADER ETH 
                                               WHERE  ETH.STANDARD = 'EDIFACT' 
                                                      AND ETH.CODE_SET = 
                                                          'IGMLINECD') 
                                 AND A.SEALINER_VALUE = P_I_V_POD_TERMINAL 
                                 AND ROWNUM < 2)             LINE_CODE, 
                         (SELECT VVPCAL 
                          FROM 
                 (SELECT VVPCAL 
                  FROM   SEALINER.ITP063 
                  WHERE  VVVESS = P_I_V_VESSEL 
                         AND VVVERS = 99 
                         AND ( VVARDT 
                               ||NVL(LPAD(VVARTM, 4, 0), '0000') ) 
                             <= 
                             (SELECT 
                             VVARDT 
                             ||NVL( 
                               LPAD(VVARTM, 4, 0), 
                                         '0000') 
                             AS 
                             ETADATE 
                             FROM 
                             SEALINER.ITP063 
                             WHERE 
                             VVSRVC = P_I_V_SERVICE 
                             AND VVPCAL = P_I_V_POD 
                             AND VVVESS = P_I_V_VESSEL 
                             AND VVVOYN = P_I_V_VOYAGE 
                             AND ( 
                                 P_I_V_POD_TERMINAL IS 
                                 NULL 
                                  OR VVTRM1 = P_I_V_POD_TERMINAL ) 
                                         AND VVVERS = 99 
                                         AND OMMISSION_FLAG IS 
                                             NULL 
                                         AND VVFORL IS NOT NULL) 
                         AND OMMISSION_FLAG IS NULL 
                         AND VVFORL IS NOT NULL 
                         AND ( VVPCAL, VVTRM1 ) NOT IN 
                             (SELECT VVPCAL, 
                                     VVTRM1 
                              FROM   SEALINER.ITP063 
                              WHERE  VVSRVC = P_I_V_SERVICE 
                                     AND VVPCAL = P_I_V_POD 
                                     AND VVVESS = P_I_V_VESSEL 
                                     AND VVVOYN = P_I_V_VOYAGE 
                                     AND ( 
                             P_I_V_POD_TERMINAL IS NULL 
                              OR VVTRM1 = P_I_V_POD_TERMINAL ) 
                                     AND VVVERS = 99 
                                     AND OMMISSION_FLAG IS NULL 
                                     AND VVFORL IS NOT NULL) 
                  ORDER  BY VVARDT 
                            ||NVL(LPAD(VVARTM, 4, 0), '0000') DESC 
                            , 
                            VVPCSQ DESC) 
                          WHERE  ROWNUM = 1)                 PORT_ORIGIN, 
                         ''                                  PORT_ARRIVAL, 
                         ''                                  LAST_PORT_1, 
                         ''                                  LAST_PORT_2, 
                         ''                                  LAST_PORT_3, 
                         'Containerised'                     VESSEL_TYPE, 
                         'containers'                        GEN_DESC, 
                         ''                                  MASTER_NAME, 
                         (SELECT VSCNCD 
                          FROM   ITP060 A 
                          WHERE  A.VSVESS = P_I_V_VESSEL 
                                 AND ROWNUM < 2)             VESSEL_NATION, 
                         ''                                  IGM_NUMBER, 
                         ''                                  IGM_DATE, 
                         (SELECT VVAPDT 
                          FROM   ITP063 A 
                          WHERE  VVSRVC = P_I_V_SERVICE 
                                 AND VVPCAL = P_I_V_POD 
                                 AND VVVESS = P_I_V_VESSEL 
                                 AND VVVOYN = P_I_V_VOYAGE 
                                 AND ( P_I_V_POD_TERMINAL IS NULL 
                                        OR VVTRM1 = P_I_V_POD_TERMINAL ) 
                                 AND A.VVVERS = 99 
                                 AND A.VVFORL IS NOT NULL 
                                 AND A.OMMISSION_FLAG IS NULL 
                                 AND ROWNUM < 2)             ARRIVAL_DATE, 
                         (SELECT VVAPTM 
                          FROM   ITP063 A 
                          WHERE  VVSRVC = P_I_V_SERVICE 
                                 AND VVPCAL = P_I_V_POD 
                                 AND VVVESS = P_I_V_VESSEL 
                                 AND VVVOYN = P_I_V_VOYAGE 
                                 AND ( P_I_V_POD_TERMINAL IS NULL 
                                        OR VVTRM1 = P_I_V_POD_TERMINAL ) 
                                 AND A.VVVERS = 99 
                                 AND A.VVFORL IS NOT NULL 
                                 AND A.OMMISSION_FLAG IS NULL 
                                 AND ROWNUM < 2)             ARRIVAL_TIME, 
                         (SELECT GROSS_TON_INTER 
                          FROM   ITP060 A 
                          WHERE  A.VSVESS = P_I_V_VESSEL 
                                 AND ROWNUM < 2)             GROSS_WEIGHT, 
                         (SELECT NET_TON_INTER 
                          FROM   ITP060 A 
                          WHERE  A.VSVESS = IDP5.ACT_VESSEL_CODE 
                                 AND ROWNUM < 2)             NET_WEIGHT, 
                         '0'                                 TOTAL_BLS, 
                         ''                                  LIGHT_DUE, 
                         'N'                                 SM_BT_CARGO, 
                         'N'                                 SHIP_STR_DECL, 
                         'N'                                 CREW_LST_DECL, 
                         'N'                                 CARGO_DECL, 
                         'N'                                 PASSNGR_LIST, 
                         'N'                                 CREW_EFFECT, 
                         'N'                                 MARITIME_DECL, 
                         ''                                  ITEM_NUMBER, 
                         'C'                                 CARGO_NATURE, 
                         'TI'                                CARGO_MOVMNT, 
                         'OT'                                ITEM_TYPE, 
                         'FCL'                               CARGO_MOVMNT_TYPE, 
                         IDP5.TRANSPORT_MODE, 
                         ''                                  ROAD_CARR_CODE, 
                         ''                                  ROAD_TP_BOND_NO, 
                         ''                                  SUBMIT_DATE_TIME, 
                         ''                                  NHAVA_SHEVA_ETA, 
                         FINAL_PLACE_OF_DELIVERY_NAME 
                         FINAL_PLACE_DELIVERY, 
                         ''                                  PACKAGES, 
                         (SELECT A.PARTNER_VALUE 
                          FROM   EDI_TRANSLATION_DETAIL A 
                          WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                                               FROM   EDI_TRANSLATION_HEADER ETH 
                                               WHERE  ETH.STANDARD = 'EDIFACT' 
                                                      AND ETH.CODE_SET = 
                                                          'IGMPORT' 
                                              ) 
                                 AND A.SEALINER_VALUE = P_I_V_POD 
                                 AND ROWNUM < 2)             CFS_NAME, 
                         ''                                  MBL_NO, 
                         ''                                  HBL_NO, 
                         ''                                  FROM_ITEM_NO, 
                         ''                                  TO_ITEM_NO 
                  FROM   IDP005 IDP5 
                         INNER JOIN IDP010 IDP10 
                                 ON IDP5.SYBLNO = IDP10.AYBLNO 
                  WHERE  IDP5.DISCHARGE_PORT = P_I_V_POD 
                         AND ( P_I_V_SERVICE IS NULL 
                                OR IDP5.ACT_SERVICE_CODE = P_I_V_SERVICE ) 
                         AND ( P_I_V_VESSEL IS NULL 
                                OR IDP5.ACT_VESSEL_CODE = P_I_V_VESSEL ) 
                         AND ( P_I_V_VOYAGE IS NULL 
                                OR IDP5.ACT_VOYAGE_NUMBER = P_I_V_VOYAGE ) 
                         AND ( P_I_V_POD_TERMINAL IS NULL 
                                OR IDP5.TO_TERMINAL = P_I_V_POD_TERMINAL ) 
                         AND ( P_I_V_FROM_DATE IS NULL 
                                OR IDP10.AYISDT >= P_I_V_FROM_DATE ) 
                         AND ( P_I_V_TO_DATE IS NULL 
                                OR IDP10.AYISDT <= P_I_V_TO_DATE ) 
                         AND ( P_I_V_BL_STATUS IS NULL 
                                OR IDP10.AYIMST = P_I_V_BL_STATUS ) 
                         AND ( P_I_V_POL IS NULL 
                                OR IDP5.LOAD_PORT = P_I_V_POL ) 
                         AND ( P_I_V_POL_TERMINAL IS NULL 
                                OR IDP5.FROM_TERMINAL = P_I_V_POL_TERMINAL ) 
                         AND IDP10.AYSORC = 'C' 
                         --AND IDP5.VOYAGE_SEQ<>1   
                         --AND IDP5.TRANSPORT_MODE in ('T','R')  
                         AND IDP5.SYBLNO NOT IN (SELECT BL_NO 
                                                 FROM   RCL_IGM_DETAILS rid 
                                                 WHERE 
                             rid.PORT = P_I_V_POD 
                             AND ( P_I_V_SERVICE IS 
                                   NULL 
                                    OR rid.SERVICE = 
                                       P_I_V_SERVICE ) 
                             AND ( P_I_V_VESSEL IS NULL 
                                    OR rid.VESSEL = 
                                       P_I_V_VESSEL ) 
                             AND ( P_I_V_VOYAGE IS NULL 
                                    OR rid.VOYAGE = 
                                       P_I_V_VOYAGE ) 
                             AND ( P_I_V_POD_TERMINAL 
                                   IS 
                                   NULL 
                                    OR rid.TERMINAL = 
                                       P_I_V_POD_TERMINAL ) 
                             AND ( P_I_V_FROM_DATE IS NULL 
                                    OR rid.BL_DATE >= 
                                       P_I_V_FROM_DATE 
                                 ) 
                             AND ( P_I_V_TO_DATE IS NULL 
                                    OR rid.BL_DATE <= 
                                       P_I_V_TO_DATE ) 
                             AND ( P_I_V_POL IS NULL 
                                    OR rid.POL = P_I_V_POL 
                                 ) 
                             AND ( P_I_V_BL_STATUS IS NULL 
                                    OR rid.BL_STATUS = 
                                       P_I_V_BL_STATUS 
                                 ) 
                             AND ( P_I_V_POL_TERMINAL IS 
                                   NULL 
                                    OR rid.POL_TERMINAL = 
                                       P_I_V_POL_TERMINAL ) 
                                                )) NS, 
                 (SELECT BL_NO, 
                         BL_STATUS, 
                         TO_NUMBER(BL_DATE)          BL_DATE, 
                         SERVICE, 
                         VESSEL, 
                         VOYAGE, 
                         ''                          DIRECTION, 
                         POL, 
                         POL_TERMINAL, 
                         PORT                        POD, 
                         TERMINAL                    POD_TERMINAL, 
                         TERMINAL, 
                         CUST_CODE, 
                         CALL_SIGN, 
                         IMO_CODE, 
                         AGENT_CODE, 
                         LINE_CODE, 
                         PORT_ORIGIN, 
                         PORT_ARRIVAL, 
                         LAST_PORT_1, 
                         LAST_PORT_2, 
                         LAST_PORT_3, 
                         VESSEL_TYPE, 
                         GEN_DESC, 
                         MASTER_NAME, 
                         VESSEL_NATION, 
                         IGM_NUMBER, 
                         IGM_DATE, 
                         TO_NUMBER(ARRIVAL_DATE_ETA) ARRIVAL_DATE, 
                         TO_NUMBER(ARRIVAL_TIME_ETA) ARRIVAL_TIME, 
                         GROSS_WEIGHT, 
                         NET_WEIGHT, 
                         TOTAL_BLS, 
                         LIGHT_DUE, 
                         SM_BT_CARGO, 
                         SHIP_STR_DECL, 
                         CREW_LST_DECL, 
                         CARGO_DECL, 
                         PASSNGR_LIST, 
                         CREW_EFFECT, 
                         MARITIME_DECL, 
                         ITEM_NUMBER, 
                         CARGO_NATURE, 
                         CARGO_MOVMNT, 
                         ITEM_TYPE, 
                         CARGO_MOVMNT_TYPE, 
                         TRANSPORT_MODE, 
                         ROAD_CARR_CODE, 
                         ROAD_TP_BOND_NO, 
                         SUBMIT_DATE_TIME, 
                         NHAVA_SHEVA_ETA, 
                         FINAL_PLACE_DELIVERY, 
                         PACKAGES, 
                         CFS_NAME, 
                         MBL_NO, 
                         HBL_NO, 
                         FROM_ITEM_NO, 
                         TO_ITEM_NO 
                  FROM   RCL_IGM_DETAILS rids 
                  WHERE  rids.PORT = P_I_V_POD 
                         AND ( P_I_V_SERVICE IS NULL 
                                OR rids.SERVICE = P_I_V_SERVICE ) 
                         AND ( P_I_V_VESSEL IS NULL 
                                OR rids.VESSEL = P_I_V_VESSEL ) 
                         AND ( P_I_V_VOYAGE IS NULL 
                                OR rids.VOYAGE = P_I_V_VOYAGE ) 
                         AND ( P_I_V_POD_TERMINAL IS NULL 
                                OR rids.TERMINAL = P_I_V_POD_TERMINAL ) 
                         AND ( P_I_V_FROM_DATE IS NULL 
                                OR rids.BL_DATE >= P_I_V_FROM_DATE ) 
                         AND ( P_I_V_TO_DATE IS NULL 
                                OR rids.BL_DATE <= P_I_V_TO_DATE ) 
                         AND ( P_I_V_POL IS NULL 
                                OR rids.POL = P_I_V_POL ) 
                         AND ( P_I_V_BL_STATUS IS NULL 
                                OR rids.BL_STATUS = P_I_V_BL_STATUS ) 
                         AND ( P_I_V_POL_TERMINAL IS NULL 
                                OR rids.POL_TERMINAL = P_I_V_POL_TERMINAL ) 
                         AND ROWNUM < 2) OS 
          WHERE  NS.SERVICE = OS.SERVICE 
                 AND NS.VESSEL = OS.VESSEL 
                 AND NS.VOYAGE = OS.VOYAGE 
                 AND NS.POD = OS.POD 
                 AND NS.TERMINAL = OS.TERMINAL 
          UNION 
          SELECT BL_NO, 
                 BL_STATUS, 
                 TO_NUMBER(BL_DATE)          BL_DATE, 
                 SERVICE, 
                 VESSEL, 
                 VOYAGE, 
                 ''                          DIRECTION, 
                 POL, 
                 POL_TERMINAL, 
                 PORT                        POD, 
                 TERMINAL                    POD_TERMINAL, 
                 TERMINAL, 
                 CUST_CODE, 
                 CALL_SIGN, 
                 IMO_CODE, 
                 AGENT_CODE, 
                 LINE_CODE, 
                 PORT_ORIGIN, 
                 PORT_ARRIVAL, 
                 LAST_PORT_1, 
                 LAST_PORT_2, 
                 LAST_PORT_3, 
                 VESSEL_TYPE, 
                 GEN_DESC, 
                 MASTER_NAME, 
                 VESSEL_NATION, 
                 IGM_NUMBER, 
                 IGM_DATE, 
                 TO_NUMBER(ARRIVAL_DATE_ETA) ARRIVAL_DATE, 
                 TO_NUMBER(ARRIVAL_TIME_ETA) ARRIVAL_TIME, 
                 GROSS_WEIGHT, 
                 NET_WEIGHT, 
                 TOTAL_BLS, 
                 LIGHT_DUE, 
                 SM_BT_CARGO, 
                 SHIP_STR_DECL, 
                 CREW_LST_DECL, 
                 CARGO_DECL, 
                 PASSNGR_LIST, 
                 CREW_EFFECT, 
                 MARITIME_DECL, 
                 ITEM_NUMBER, 
                 CARGO_NATURE, 
                 CARGO_MOVMNT, 
                 ITEM_TYPE, 
                 CARGO_MOVMNT_TYPE, 
                 TRANSPORT_MODE, 
                 ROAD_CARR_CODE, 
                 ROAD_TP_BOND_NO, 
                 SUBMIT_DATE_TIME, 
                 NHAVA_SHEVA_ETA, 
                 FINAL_PLACE_DELIVERY, 
                 PACKAGES, 
                 CFS_NAME, 
                 MBL_NO, 
                 HBL_NO, 
                 FROM_ITEM_NO, 
                 TO_ITEM_NO 
          FROM   RCL_IGM_DETAILS rid 
          WHERE  rid.PORT = P_I_V_POD 
                 AND ( P_I_V_SERVICE IS NULL 
                        OR rid.SERVICE = P_I_V_SERVICE ) 
                 AND ( P_I_V_VESSEL IS NULL 
                        OR rid.VESSEL = P_I_V_VESSEL ) 
                 AND ( P_I_V_VOYAGE IS NULL 
                        OR rid.VOYAGE = P_I_V_VOYAGE ) 
                 AND ( P_I_V_POD_TERMINAL IS NULL 
                        OR rid.TERMINAL = P_I_V_POD_TERMINAL ) 
                 AND ( P_I_V_FROM_DATE IS NULL 
                        OR rid.BL_DATE >= P_I_V_FROM_DATE ) 
                 AND ( P_I_V_TO_DATE IS NULL 
                        OR rid.BL_DATE <= P_I_V_TO_DATE ) 
                 AND ( P_I_V_POL IS NULL 
                        OR rid.POL = P_I_V_POL ) 
                 AND ( P_I_V_BL_STATUS IS NULL 
                        OR rid.BL_STATUS = P_I_V_BL_STATUS ) 
                 AND ( P_I_V_POL_TERMINAL IS NULL 
                        OR rid.POL_TERMINAL = P_I_V_POL_TERMINAL ); 
      END IF; 
  EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
               P_O_V_ERROR := '1111'; WHEN OTHERS THEN 
               P_O_V_ERROR := SUBSTR(SQLCODE, 1, 10) 
                              || ':' 
                              || SUBSTR(SQLERRM, 1, 100); 
  END RCL_IGM_GET_DATA; 
  PROCEDURE RCL_IGM_SAVE_DATA (P_I_V_SERVICE              VARCHAR2 DEFAULT NULL, 
                               P_I_V_VESSEL               VARCHAR2 DEFAULT NULL, 
                               P_I_V_VOYAGE               VARCHAR2 DEFAULT NULL, 
                               P_I_V_PORT                 VARCHAR2 DEFAULT NULL, 
                               P_I_V_TERMINAL             VARCHAR2 DEFAULT NULL, 
                               P_I_V_NEW_VESSEL           VARCHAR2 DEFAULT NULL, 
                               P_I_V_NEW_VOYAGE           VARCHAR2 DEFAULT NULL, 
                               P_I_V_FROM_ITEM_NO         VARCHAR2 DEFAULT NULL, 
                               P_I_V_TO_ITEM_NO           VARCHAR2 DEFAULT NULL, 
                               P_I_V_ROAD_CARR_CODE       VARCHAR2 DEFAULT NULL, 
                               P_I_V_ROAD_TP_BOND_NO      VARCHAR2 DEFAULT NULL, 
                               P_I_V_CUST_CODE            VARCHAR2 DEFAULT NULL, 
                               P_I_V_CALL_SIGN            VARCHAR2 DEFAULT NULL, 
                               P_I_V_IMO_CODE             VARCHAR2 DEFAULT NULL, 
                               P_I_V_AGENT_CODE           VARCHAR2 DEFAULT NULL, 
                               P_I_V_LINE_CODE            VARCHAR2 DEFAULT NULL, 
                               P_I_V_PORT_ORIGIN          VARCHAR2 DEFAULT NULL, 
                               P_I_V_PORT_ARRIVAL         VARCHAR2 DEFAULT NULL, 
                               P_I_V_LAST_PORT_1          VARCHAR2 DEFAULT NULL, 
                               P_I_V_LAST_PORT_2          VARCHAR2 DEFAULT NULL, 
                               P_I_V_LAST_PORT_3          VARCHAR2 DEFAULT NULL, 
                               P_I_V_VESSEL_TYPE          VARCHAR2 DEFAULT NULL, 
                               P_I_V_GEN_DESC             VARCHAR2 DEFAULT NULL, 
                               P_I_V_MASTER_NAME          VARCHAR2 DEFAULT NULL, 
                               P_I_V_IGM_NUMBER           VARCHAR2 DEFAULT NULL, 
                               P_I_V_IGM_DATE             VARCHAR2 DEFAULT NULL, 
                               P_I_V_VESSEL_NATION        VARCHAR2 DEFAULT NULL, 
                               P_I_V_ARRIVAL_DATE_ETA     VARCHAR2 DEFAULT NULL, 
                               P_I_V_ARRIVAL_TIME_ETA     VARCHAR2 DEFAULT NULL, 
                               P_I_V_ARRIVAL_DATE_ATA     VARCHAR2 DEFAULT NULL, 
                               P_I_V_ARRIVAL_TIME_ATA     VARCHAR2 DEFAULT NULL, 
                               P_I_V_TOTAL_BLS            VARCHAR2 DEFAULT NULL, 
                               P_I_V_LIGHT_DUE            VARCHAR2 DEFAULT NULL, 
                               P_I_V_GROSS_WEIGHT         VARCHAR2 DEFAULT NULL, 
                               P_I_V_NET_WEIGHT           VARCHAR2 DEFAULT NULL, 
                               P_I_V_SM_BT_CARGO          VARCHAR2 DEFAULT NULL, 
                               P_I_V_SHIP_STR_DECL        VARCHAR2 DEFAULT NULL, 
                               P_I_V_CREW_LST_DECL        VARCHAR2 DEFAULT NULL, 
                               P_I_V_CARGO_DECL           VARCHAR2 DEFAULT NULL, 
                               P_I_V_PASSNGR_LIST         VARCHAR2 DEFAULT NULL, 
                               P_I_V_CREW_EFFECT          VARCHAR2 DEFAULT NULL, 
                               P_I_V_MARITIME_DECL        VARCHAR2 DEFAULT NULL, 
                               P_I_V_ITEM_NUMBER          VARCHAR2 DEFAULT NULL, 
                               P_I_V_BL_NO                VARCHAR2 DEFAULT NULL, 
                               P_I_V_CFS_NAME             VARCHAR2 DEFAULT NULL, 
                               P_I_V_CARGO_NATURE         VARCHAR2 DEFAULT NULL, 
                               P_I_V_CARGO_MOVMNT         VARCHAR2 DEFAULT NULL, 
                               P_I_V_ITEM_TYPE            VARCHAR2 DEFAULT NULL, 
                               P_I_V_CARGO_MOVMNT_TYPE    VARCHAR2 DEFAULT NULL, 
                               P_I_V_TRANSPORT_MODE       VARCHAR2 DEFAULT NULL, 
                               P_I_V_SUBMIT_DATE_TIME     VARCHAR2 DEFAULT NULL, 
                               P_I_V_DIRECTION            VARCHAR2 DEFAULT NULL, 
                               P_I_V_BL_DATE              VARCHAR2 DEFAULT NULL, 
                               P_I_V_MBL_NO               VARCHAR2 DEFAULT NULL, 
                               P_I_V_HBL_NO               VARCHAR2 DEFAULT NULL, 
                               P_I_V_PACKAGES             VARCHAR2 DEFAULT NULL, 
                               P_I_V_NHAVA_SHEVA_ETA      VARCHAR2 DEFAULT NULL, 
                               P_I_V_FINAL_PLACE_DELIVERY VARCHAR2 DEFAULT NULL, 
                               P_I_V_POL                  VARCHAR2 DEFAULT NULL, 
                               P_I_V_POL_TERMINAL         VARCHAR2 DEFAULT NULL, 
                               P_I_V_BL_STATUS            VARCHAR2 DEFAULT NULL, 
                               P_I_V_ADD_USER             VARCHAR2 DEFAULT NULL, 
                               P_I_V_ADD_DATE             VARCHAR2 DEFAULT NULL, 
                               P_I_V_CHANGE_USER          VARCHAR2 DEFAULT NULL, 
                               P_I_V_CHANGE_DATE          VARCHAR2 DEFAULT NULL, 
                               P_I_V_IS_PRESENT_IN_DB     VARCHAR2 DEFAULT NULL, 
                               P_I_V_IS_SELECTED          VARCHAR2 DEFAULT NULL, 
                               P_O_V_ERROR                OUT VARCHAR2) 
  IS 
  BEGIN 
      P_O_V_ERROR := '000000'; 

      IF P_I_V_IS_PRESENT_IN_DB IN ( 'TRUE' ) 
         AND P_I_V_IS_SELECTED IN ( 'FALSE' ) THEN 
        DELETE FROM RCL_IGM_DETAILS 
        WHERE  SERVICE = P_I_V_SERVICE 
               AND VESSEL = P_I_V_VESSEL 
               AND VOYAGE = P_I_V_VOYAGE 
               AND PORT = P_I_V_PORT 
               AND TERMINAL = P_I_V_TERMINAL 
               AND BL_NO = P_I_V_BL_NO; 
      ELSE 
        UPDATE RCL_IGM_DETAILS 
        SET    NEW_VESSEL = P_I_V_NEW_VESSEL, 
               NEW_VOYAGE = P_I_V_NEW_VOYAGE, 
               FROM_ITEM_NO = P_I_V_FROM_ITEM_NO, 
               TO_ITEM_NO = P_I_V_TO_ITEM_NO, 
               ROAD_CARR_CODE = P_I_V_ROAD_CARR_CODE, 
               ROAD_TP_BOND_NO = P_I_V_ROAD_TP_BOND_NO, 
               CUST_CODE = P_I_V_CUST_CODE, 
               CALL_SIGN = P_I_V_CALL_SIGN, 
               IMO_CODE = P_I_V_IMO_CODE, 
               AGENT_CODE = P_I_V_AGENT_CODE, 
               LINE_CODE = P_I_V_LINE_CODE, 
               PORT_ORIGIN = P_I_V_PORT_ORIGIN, 
               PORT_ARRIVAL = P_I_V_PORT_ARRIVAL, 
               LAST_PORT_1 = P_I_V_LAST_PORT_1, 
               LAST_PORT_2 = P_I_V_LAST_PORT_2, 
               LAST_PORT_3 = P_I_V_LAST_PORT_3, 
               VESSEL_TYPE = P_I_V_VESSEL_TYPE, 
               GEN_DESC = P_I_V_GEN_DESC, 
               MASTER_NAME = P_I_V_MASTER_NAME, 
               IGM_NUMBER = P_I_V_IGM_NUMBER, 
               IGM_DATE = P_I_V_IGM_DATE, 
               VESSEL_NATION = P_I_V_VESSEL_NATION, 
               ARRIVAL_DATE_ETA = P_I_V_ARRIVAL_DATE_ETA, 
               ARRIVAL_TIME_ETA = P_I_V_ARRIVAL_TIME_ETA, 
               ARRIVAL_DATE_ATA = P_I_V_ARRIVAL_DATE_ATA, 
               ARRIVAL_TIME_ATA = P_I_V_ARRIVAL_TIME_ATA, 
               TOTAL_BLS = P_I_V_TOTAL_BLS, 
               LIGHT_DUE = P_I_V_LIGHT_DUE, 
               GROSS_WEIGHT = TO_NUMBER(P_I_V_GROSS_WEIGHT), 
               NET_WEIGHT = TO_NUMBER(P_I_V_NET_WEIGHT), 
               SM_BT_CARGO = P_I_V_SM_BT_CARGO, 
               SHIP_STR_DECL = P_I_V_SHIP_STR_DECL, 
               CREW_LST_DECL = P_I_V_CREW_LST_DECL, 
               CARGO_DECL = P_I_V_CARGO_DECL, 
               PASSNGR_LIST = P_I_V_PASSNGR_LIST, 
               CREW_EFFECT = P_I_V_CREW_EFFECT, 
               MARITIME_DECL = P_I_V_MARITIME_DECL, 
               ITEM_NUMBER = P_I_V_ITEM_NUMBER, 
               CFS_NAME = P_I_V_CFS_NAME, 
               CARGO_NATURE = P_I_V_CARGO_NATURE, 
               CARGO_MOVMNT = P_I_V_CARGO_MOVMNT, 
               ITEM_TYPE = P_I_V_ITEM_TYPE, 
               CARGO_MOVMNT_TYPE = P_I_V_CARGO_MOVMNT_TYPE, 
               TRANSPORT_MODE = P_I_V_TRANSPORT_MODE, 
               SUBMIT_DATE_TIME = P_I_V_SUBMIT_DATE_TIME, 
               DIRECTION = P_I_V_DIRECTION, 
               BL_DATE = P_I_V_BL_DATE, 
               MBL_NO = P_I_V_MBL_NO, 
               HBL_NO = P_I_V_HBL_NO, 
               PACKAGES = P_I_V_PACKAGES, 
               NHAVA_SHEVA_ETA = P_I_V_NHAVA_SHEVA_ETA, 
               FINAL_PLACE_DELIVERY = P_I_V_FINAL_PLACE_DELIVERY, 
               BL_STATUS = P_I_V_BL_STATUS, 
               POL = P_I_V_POL, 
               POL_TERMINAL = P_I_V_POL_TERMINAL 
        WHERE  SERVICE = P_I_V_SERVICE 
               AND VESSEL = P_I_V_VESSEL 
               AND VOYAGE = P_I_V_VOYAGE 
               AND PORT = P_I_V_PORT 
               AND TERMINAL = P_I_V_TERMINAL 
               AND BL_NO = P_I_V_BL_NO; 

        IF SQL%ROWCOUNT = 0 THEN 
          DBMS_OUTPUT.PUT_LINE('Record inserted..!!'); 

          INSERT INTO RCL_IGM_DETAILS 
                      (SERVICE, 
                       VESSEL, 
                       VOYAGE, 
                       PORT, 
                       TERMINAL, 
                       NEW_VESSEL, 
                       NEW_VOYAGE, 
                       FROM_ITEM_NO, 
                       TO_ITEM_NO, 
                       ROAD_CARR_CODE, 
                       ROAD_TP_BOND_NO, 
                       CUST_CODE, 
                       CALL_SIGN, 
                       IMO_CODE, 
                       AGENT_CODE, 
                       LINE_CODE, 
                       PORT_ORIGIN, 
                       PORT_ARRIVAL, 
                       LAST_PORT_1, 
                       LAST_PORT_2, 
                       LAST_PORT_3, 
                       VESSEL_TYPE, 
                       GEN_DESC, 
                       MASTER_NAME, 
                       IGM_NUMBER, 
                       IGM_DATE, 
                       VESSEL_NATION, 
                       ARRIVAL_DATE_ETA, 
                       ARRIVAL_TIME_ETA, 
                       ARRIVAL_DATE_ATA, 
                       ARRIVAL_TIME_ATA, 
                       TOTAL_BLS, 
                       LIGHT_DUE, 
                       GROSS_WEIGHT, 
                       NET_WEIGHT, 
                       SM_BT_CARGO, 
                       SHIP_STR_DECL, 
                       CREW_LST_DECL, 
                       CARGO_DECL, 
                       PASSNGR_LIST, 
                       CREW_EFFECT, 
                       MARITIME_DECL, 
                       ITEM_NUMBER, 
                       BL_NO, 
                       CFS_NAME, 
                       CARGO_NATURE, 
                       CARGO_MOVMNT, 
                       ITEM_TYPE, 
                       CARGO_MOVMNT_TYPE, 
                       TRANSPORT_MODE, 
                       SUBMIT_DATE_TIME, 
                       DIRECTION, 
                       BL_DATE, 
                       MBL_NO, 
                       HBL_NO, 
                       PACKAGES, 
                       NHAVA_SHEVA_ETA, 
                       FINAL_PLACE_DELIVERY, 
                       POL, 
                       POL_TERMINAL, 
                       BL_STATUS) 
          VALUES      ( P_I_V_SERVICE, 
                       P_I_V_VESSEL, 
                       P_I_V_VOYAGE, 
                       P_I_V_PORT, 
                       P_I_V_TERMINAL, 
                       P_I_V_NEW_VESSEL, 
                       P_I_V_NEW_VOYAGE, 
                       P_I_V_FROM_ITEM_NO, 
                       P_I_V_TO_ITEM_NO, 
                       P_I_V_ROAD_CARR_CODE, 
                       P_I_V_ROAD_TP_BOND_NO, 
                       P_I_V_CUST_CODE, 
                       P_I_V_CALL_SIGN, 
                       P_I_V_IMO_CODE, 
                       P_I_V_AGENT_CODE, 
                       P_I_V_LINE_CODE, 
                       P_I_V_PORT_ORIGIN, 
                       P_I_V_PORT_ARRIVAL, 
                       P_I_V_LAST_PORT_1, 
                       P_I_V_LAST_PORT_2, 
                       P_I_V_LAST_PORT_3, 
                       P_I_V_VESSEL_TYPE, 
                       P_I_V_GEN_DESC, 
                       P_I_V_MASTER_NAME, 
                       P_I_V_IGM_NUMBER, 
                       P_I_V_IGM_DATE, 
                       P_I_V_VESSEL_NATION, 
                       P_I_V_ARRIVAL_DATE_ETA, 
                       P_I_V_ARRIVAL_TIME_ETA, 
                       P_I_V_ARRIVAL_DATE_ATA, 
                       P_I_V_ARRIVAL_TIME_ATA, 
                       P_I_V_TOTAL_BLS, 
                       P_I_V_LIGHT_DUE, 
                       TO_NUMBER(P_I_V_GROSS_WEIGHT), 
                       TO_NUMBER(P_I_V_NET_WEIGHT), 
                       P_I_V_SM_BT_CARGO, 
                       P_I_V_SHIP_STR_DECL, 
                       P_I_V_CREW_LST_DECL, 
                       P_I_V_CARGO_DECL, 
                       P_I_V_PASSNGR_LIST, 
                       P_I_V_CREW_EFFECT, 
                       P_I_V_MARITIME_DECL, 
                       P_I_V_ITEM_NUMBER, 
                       P_I_V_BL_NO, 
                       P_I_V_CFS_NAME, 
                       P_I_V_CARGO_NATURE, 
                       P_I_V_CARGO_MOVMNT, 
                       P_I_V_ITEM_TYPE, 
                       P_I_V_CARGO_MOVMNT_TYPE, 
                       P_I_V_TRANSPORT_MODE, 
                       P_I_V_SUBMIT_DATE_TIME, 
                       P_I_V_DIRECTION, 
                       P_I_V_BL_DATE, 
                       P_I_V_MBL_NO, 
                       P_I_V_HBL_NO, 
                       P_I_V_PACKAGES, 
                       P_I_V_NHAVA_SHEVA_ETA, 
                       P_I_V_FINAL_PLACE_DELIVERY, 
                       P_I_V_POL, 
                       P_I_V_POL_TERMINAL, 
                       P_I_V_BL_STATUS ); 
        ELSE 
          DBMS_OUTPUT.PUT_LINE('Record updated..!!'); 
        END IF; 

        UPDATE RCL_IGM_DETAILS 
        SET    NEW_VESSEL = P_I_V_NEW_VESSEL, 
               NEW_VOYAGE = P_I_V_NEW_VOYAGE, 
               FROM_ITEM_NO = P_I_V_FROM_ITEM_NO, 
               TO_ITEM_NO = P_I_V_TO_ITEM_NO, 
               CUST_CODE = P_I_V_CUST_CODE, 
               CALL_SIGN = P_I_V_CALL_SIGN, 
               IMO_CODE = P_I_V_IMO_CODE, 
               AGENT_CODE = P_I_V_AGENT_CODE, 
               LINE_CODE = P_I_V_LINE_CODE, 
               PORT_ORIGIN = P_I_V_PORT_ORIGIN, 
               PORT_ARRIVAL = P_I_V_PORT_ARRIVAL, 
               LAST_PORT_1 = P_I_V_LAST_PORT_1, 
               LAST_PORT_2 = P_I_V_LAST_PORT_2, 
               LAST_PORT_3 = P_I_V_LAST_PORT_3, 
               VESSEL_TYPE = P_I_V_VESSEL_TYPE, 
               GEN_DESC = P_I_V_GEN_DESC, 
               MASTER_NAME = P_I_V_MASTER_NAME, 
               IGM_NUMBER = P_I_V_IGM_NUMBER, 
               IGM_DATE = P_I_V_IGM_DATE, 
               VESSEL_NATION = P_I_V_VESSEL_NATION, 
               ARRIVAL_DATE_ETA = P_I_V_ARRIVAL_DATE_ETA, 
               ARRIVAL_TIME_ETA = P_I_V_ARRIVAL_TIME_ETA, 
               ARRIVAL_DATE_ATA = P_I_V_ARRIVAL_DATE_ATA, 
               ARRIVAL_TIME_ATA = P_I_V_ARRIVAL_TIME_ATA, 
               TOTAL_BLS = P_I_V_TOTAL_BLS, 
               LIGHT_DUE = P_I_V_LIGHT_DUE, 
               GROSS_WEIGHT = TO_NUMBER(P_I_V_GROSS_WEIGHT), 
               NET_WEIGHT = TO_NUMBER(P_I_V_NET_WEIGHT), 
               SM_BT_CARGO = P_I_V_SM_BT_CARGO, 
               SHIP_STR_DECL = P_I_V_SHIP_STR_DECL, 
               CREW_LST_DECL = P_I_V_CREW_LST_DECL, 
               CARGO_DECL = P_I_V_CARGO_DECL, 
               PASSNGR_LIST = P_I_V_PASSNGR_LIST, 
               CREW_EFFECT = P_I_V_CREW_EFFECT, 
               MARITIME_DECL = P_I_V_MARITIME_DECL 
        WHERE  SERVICE = P_I_V_SERVICE 
               AND VESSEL = P_I_V_VESSEL 
               AND VOYAGE = P_I_V_VOYAGE 
               AND PORT = P_I_V_PORT 
               AND TERMINAL = P_I_V_TERMINAL; 
      END IF; 
  EXCEPTION 
    WHEN NO_DATA_FOUND THEN 
               P_O_V_ERROR := '1111'; WHEN OTHERS THEN 
               P_O_V_ERROR := SUBSTR(SQLCODE, 1, 10) 
                              || ':' 
                              || SUBSTR(SQLERRM, 1, 100); 
  END RCL_IGM_SAVE_DATA; 
  
  
  
        
        
PROCEDURE RCL_IGM_GET_SAVE_CONTAINOR(
                                P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
                                P_I_V_BL            VARCHAR2 DEFAULT NULL,
                                P_O_V_ERROR         OUT VARCHAR2)     IS 
                            
                                                        
                                    l_v_sql VARCHAR2(10000);  
  
        BEGIN   
  
            
       l_v_sql:=' SELECT  PK_BL_NO FK_BL_NO,
                DN_CONTAINER_NO, 
                 EQUIPMENT_SEAL_TYPE  SEALTYPE ,
               CUSTOMER_SEAL_NO  SEAL_NO,
                CONTAINER_AGENT,
               EQUIPMENT_STATUS  CONTAINER_STATUS,
               SOC_FLAG,
                 CONTAINER_BONDFLAG,
              DN_EQ_TYPE   CONTAINERTYPE,
               TYPE_DETAIL,
              QTY_PACKAGES  TOTAL_PACKAGES,         
            METRIC_WEIGHT  CONTAINER_WEIGHT,
             CBM,
             ISO_CODE,
             UNOS,
            IMO,
            CLASSIFICATION,
             REMARKS,
            DN_EQ_SIZE  CONTAINERSIZE
           			 FROM  IGM_CONTAINER_DETAILS  
						 WHERE  PK_BL_NO IN ('||P_I_V_BL||')';
     insert into chandra values('####errro#######'||l_v_sql);  commit; 
     
  
        
    OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    
      END RCL_IGM_GET_SAVE_CONTAINOR;
      
      
END RCL_IGM_TEST_INFO;