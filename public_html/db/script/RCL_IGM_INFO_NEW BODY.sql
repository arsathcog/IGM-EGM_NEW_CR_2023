create or replace PACKAGE BODY    RCL_IGM_INFO_NEW
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
                             P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                             P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                             P_I_V_POL_TERMINAL VARCHAR2 DEFAULT NULL,
                             P_I_V_DIRECTION    VARCHAR2 DEFAULT NULL,
                             P_O_V_ERROR        OUT VARCHAR2)
  IS

  BEGIN



        OPEN P_O_REFIGMTABFIND FOR
         SELECT
                       --IDP10.AYIMST                        BL_STATUS,
                         ''                                  BL_STATUS,
                         ''                                  BL_DATE,
                         ACT_SERVICE_CODE                    SERVICE,
                         ACT_VESSEL_CODE                     VESSEL,
                         ACT_VOYAGE_NUMBER                   VOYAGE,
                       --ACT_PORT_DIRECTION                  DIRECTION,
                         ''                                  DIRECTION,
                       --LOAD_PORT                           POL,
                         ''                                  POL,
                       --FROM_TERMINAL                       POL_TERMINAL,
                         ''                                  POL_TERMINAL,
                       --IDP10.AYDEST                        DEL_VLS,
                         ''                                  DEL_VLS,
                         TO_TERMINAL                         DEPOT_VLS,
                         DISCHARGE_PORT                      POD,
                         TO_TERMINAL                         POD_TERMINAL,
                         TO_TERMINAL                         TERMINAL,
                         ''                                  BL_NO,
                         ''                                CUSTOM_TERMINAL_CODE
        FROM   IDP005 IDP5
                         INNER JOIN IDP010 IDP10
                                 ON IDP5.SYBLNO = IDP10.AYBLNO
                  WHERE
                         IDP5.DISCHARGE_PORT = P_I_V_POD
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
                 AND ( IDP10.AYIMST <> 9)
                 AND ( P_I_V_BL_STATUS IS NULL
                        OR IDP10.AYIMST = P_I_V_BL_STATUS)
                 AND ( P_I_V_POL IS NULL
                        OR IDP5.LOAD_PORT = P_I_V_POL )
                 AND ( P_I_V_POL_TERMINAL IS NULL
                        OR IDP5.FROM_TERMINAL = P_I_V_POL_TERMINAL )
                 AND ( P_I_V_DEL IS NULL
                        OR IDP10.AYDEST = P_I_V_DEL )
                 AND ( P_I_V_DEPOT IS NULL
                        OR IDP5.TO_TERMINAL = P_I_V_DEPOT )
                 AND IDP10.AYSORC = 'C'
        GROUP BY TO_TERMINAL,ACT_SERVICE_CODE,ACT_VESSEL_CODE,
                 ACT_VOYAGE_NUMBER,DISCHARGE_PORT;




      END RCL_IGM_GET_DATA;


      PROCEDURE RCL_IGM_VESSLE_VOYAGE_GET_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR,
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
                             P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                             P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                             P_I_V_POL_TERMINAL VARCHAR2 DEFAULT NULL,
                             P_I_V_DIRECTION    VARCHAR2 DEFAULT NULL,
                             P_O_V_ERROR        OUT VARCHAR2)
  IS
						BL_COUNT NUMBER(20);
						SERVISE_COUNT NUMBER(20);
						TEMP_POD_COUNT_PRIVIOUS NUMBER(2);
						TEMP_POD_COUNT_NEXT NUMBER(4);
						TEMP_LAST_PORT_1  VARCHAR2(20);     --LAST_PORT_1
						TEMP_LAST_PORT_TER_1  VARCHAR2(20);
						TEMP_LAST_PORT_2  VARCHAR2(20);     --LAST_PORT_2
						TEMP_LAST_PORT_TER_2  VARCHAR2(20);
						TEMP_LAST_PORT_3  VARCHAR2(20);     --LAST_PORT_3
						TEMP_LAST_PORT_TER_3 VARCHAR2(20);
						TEMP_NEXT_PORT_1  VARCHAR2(20);     -- FOR 1ST NEXT PORT  NEXT_PORT_4
						TEMP_NEXT_PORT_2  VARCHAR2(20);     -- FOR 2ND NEXT PORT  NEXT_PORT_5
						TEMP_NEXT_PORT_3  VARCHAR2(20);     -- FOR 3RD NEXT PORT  NEXT_PORT_6
						TEMP_SERVICE VARCHAR2(20);
						TEMP_POD_TERMINAL VARCHAR2(20);
						V_SQL   VARCHAR2(6000);
						V_SQL_CNDTN  VARCHAR2(4000);
  BEGIN

  SELECT TO_TERMINAL INTO TEMP_POD_TERMINAL FROM IDP005 WHERE ACT_VESSEL_CODE=P_I_V_VESSEL
            AND ACT_VOYAGE_NUMBER=P_I_V_VOYAGE AND DISCHARGE_PORT=P_I_V_POD AND ACT_SERVICE_CODE=P_I_V_SERVICE AND ROWNUM=1;



  --INSERT INTO chandra VALUES('1 '||TEMP_POD_TERMINAL);  commit;

    FOR TEMP_POD_COUNT_PRIVIOUS in 1 .. 3 LOOP

      IF(TEMP_POD_COUNT_PRIVIOUS=1) THEN

        BEGIN
            -- INSERT INTO chandra VALUES('2  '||TEMP_POD_TERMINAL);  commit;

            SELECT VVPCAL,VVTRM1 INTO TEMP_LAST_PORT_3,TEMP_LAST_PORT_TER_3 FROM (
            SELECT VVPCAL,VVTRM1  FROM SEALINER.ITP063 WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99 AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))<=(
            SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000') AS ETADATE FROM SEALINER.ITP063 WHERE
            VVSRVC=P_I_V_SERVICE AND
            VVPCAL=P_I_V_POD AND
            VVVESS=P_I_V_VESSEL AND
            VVVOYN=P_I_V_VOYAGE AND
            VVTRM1=TEMP_POD_TERMINAL AND
           -- VVPCSQ=PORTSEQUENCE' AND
            VVVERS=99 AND
            OMMISSION_FLAG IS NULL AND
            VVFORL IS NOT NULL )
            AND OMMISSION_FLAG IS NULL
            AND VVFORL IS NOT NULL
            AND (VVPCAL ,VVTRM1) NOT IN
            (SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
            VVSRVC= P_I_V_SERVICE AND
            VVPCAL=P_I_V_POD AND
            VVVESS=P_I_V_VESSEL AND
            VVVOYN=P_I_V_VOYAGE AND
            VVTRM1=TEMP_POD_TERMINAL AND
            --VVPCSQ=PORTSEQUENCE' AND
            VVVERS=99 AND
            OMMISSION_FLAG IS NULL AND
            VVFORL IS NOT NULL)
            ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000') DESC,VVPCSQ DESC)WHERE ROWNUM=1;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('FRESH SEARCH..!!');

        END;

         ELSIF(TEMP_POD_COUNT_PRIVIOUS=2 AND TEMP_LAST_PORT_3 IS NOT NULL ) THEN

        BEGIN
            SELECT VVPCAL,VVTRM1 INTO TEMP_LAST_PORT_2,TEMP_LAST_PORT_TER_2 FROM (
            SELECT VVPCAL,VVTRM1  FROM SEALINER.ITP063 WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99 AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))<=(
            SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000') AS ETADATE FROM SEALINER.ITP063 WHERE
            VVSRVC=P_I_V_SERVICE AND
            VVPCAL=TEMP_LAST_PORT_3 AND
            VVVESS=P_I_V_VESSEL AND
            VVVOYN=P_I_V_VOYAGE AND
            VVTRM1=TEMP_LAST_PORT_TER_3 AND
            --VVPCSQ=PORTSEQUENCE' AND
            VVVERS=99 AND
            OMMISSION_FLAG IS NULL AND
            VVFORL IS NOT NULL )
            AND OMMISSION_FLAG IS NULL
            AND VVFORL IS NOT NULL
            AND (VVPCAL ,VVTRM1) NOT IN
            (SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
            VVSRVC=P_I_V_SERVICE AND
            VVPCAL=TEMP_LAST_PORT_3 AND
            VVVESS=P_I_V_VESSEL AND
            VVVOYN=P_I_V_VOYAGE AND
            VVTRM1=TEMP_LAST_PORT_TER_3 AND
            --VVPCSQ=PORTSEQUENCE' AND
            VVVERS=99 AND
            OMMISSION_FLAG IS NULL AND
            VVFORL IS NOT NULL)
            ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000') DESC,VVPCSQ DESC)WHERE ROWNUM=1;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('FRESH SEARCH..!!');

        END;

         ELSIF(TEMP_POD_COUNT_PRIVIOUS=3 AND TEMP_LAST_PORT_2 IS NOT NULL) THEN

        BEGIN

                      --     INSERT INTO chandra VALUES('3    '||TEMP_POD_TERMINAL);  commit;
            SELECT VVPCAL INTO TEMP_LAST_PORT_1 FROM (
            SELECT VVPCAL FROM SEALINER.ITP063 WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99 AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))<=(
            SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000') AS ETADATE FROM SEALINER.ITP063 WHERE
            VVSRVC=P_I_V_SERVICE AND
            VVPCAL=TEMP_LAST_PORT_2 AND
            VVVESS=P_I_V_VESSEL AND
            VVVOYN=P_I_V_VOYAGE AND
            VVTRM1=TEMP_LAST_PORT_TER_2 AND
            --VVPCSQ=PORTSEQUENCE' AND
            VVVERS=99 AND
            OMMISSION_FLAG IS NULL AND
            VVFORL IS NOT NULL )
            AND OMMISSION_FLAG IS NULL
            AND VVFORL IS NOT NULL
            AND (VVPCAL ,VVTRM1) NOT IN
            (SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
            VVSRVC=P_I_V_SERVICE AND
            VVPCAL=TEMP_LAST_PORT_2 AND
            VVVESS=P_I_V_VESSEL AND
            VVVOYN=P_I_V_VOYAGE AND
            VVTRM1=TEMP_LAST_PORT_TER_2 AND
            --VVPCSQ=PORTSEQUENCE' AND
            VVVERS=99 AND
            OMMISSION_FLAG IS NULL AND
            VVFORL IS NOT NULL)
            ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000') DESC,VVPCSQ DESC)WHERE ROWNUM=1;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('FRESH SEARCH..!!');

        END;

         END IF;
      END LOOP;

     FOR TEMP_POD_COUNT_PRIVIOUS in 1 .. 3 LOOP

      IF(TEMP_POD_COUNT_PRIVIOUS=1) THEN



        BEGIN
            --              INSERT INTO chandra VALUES('4  '||TEMP_POD_TERMINAL);  commit;
            SELECT VVPCAL,VVTRM1 into TEMP_NEXT_PORT_1,TEMP_LAST_PORT_TER_1 FROM (
			SELECT VVPCAL,VVTRM1 FROM SEALINER.ITP063 WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99 AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))>=(
			SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000') AS ETADATE FROM SEALINER.ITP063 WHERE
			VVSRVC=P_I_V_SERVICE AND
			VVPCAL=P_I_V_POD AND
			VVVESS=P_I_V_VESSEL AND
			VVVOYN=P_I_V_VOYAGE AND
			VVTRM1=TEMP_POD_TERMINAL AND
			--VVPCSQ=PORTSEQUENCE' AND
			VVVERS=99 AND
			OMMISSION_FLAG IS NULL AND
			VVFORL IS NOT NULL )
			AND OMMISSION_FLAG IS NULL
			AND VVFORL IS NOT NULL
			AND (VVPCAL ,VVTRM1) NOT IN
			( SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
			VVSRVC =P_I_V_SERVICE AND
			VVPCAL =P_I_V_POD AND
			VVVESS =P_I_V_VESSEL AND
			VVVOYN =P_I_V_VOYAGE AND
			VVTRM1=TEMP_POD_TERMINAL AND
			--VVPCSQ =PORTSEQUENCE' AND
			VVVERS =99 AND
			OMMISSION_FLAG IS NULL AND
			VVFORL IS NOT NULL)
			ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000'),VVPCSQ)WHERE ROWNUM=1;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('FRESH SEARCH..!!');

        END;

         ELSIF(TEMP_POD_COUNT_PRIVIOUS=2 AND TEMP_NEXT_PORT_1 IS NOT NULL ) THEN

		BEGIN
                         --   INSERT INTO chandra VALUES('5  '||TEMP_POD_TERMINAL);  commit;
            SELECT VVPCAL,VVTRM1 INTO TEMP_NEXT_PORT_2,TEMP_LAST_PORT_TER_2 FROM (
			SELECT VVPCAL,VVTRM1 FROM SEALINER.ITP063 WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99 AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))>=(
			SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000') AS ETADATE FROM SEALINER.ITP063 WHERE
			VVSRVC=P_I_V_SERVICE AND
			VVPCAL=TEMP_NEXT_PORT_1 AND
			VVVESS=P_I_V_VESSEL AND
			VVVOYN=P_I_V_VOYAGE AND
			VVTRM1=TEMP_LAST_PORT_TER_1 AND
			--VVPCSQ=PORTSEQUENCE' AND
			VVVERS=99 AND
			OMMISSION_FLAG IS NULL AND
			VVFORL IS NOT NULL )
			AND OMMISSION_FLAG IS NULL
			AND VVFORL IS NOT NULL
			AND (VVPCAL ,VVTRM1) NOT IN
			( SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
			VVSRVC =P_I_V_SERVICE AND
			VVPCAL =TEMP_NEXT_PORT_1 AND
			VVVESS =P_I_V_VESSEL AND
			VVVOYN =P_I_V_VOYAGE AND
			VVTRM1=TEMP_LAST_PORT_TER_1 AND
			--VVPCSQ =PORTSEQUENCE' AND
			VVVERS =99 AND
			OMMISSION_FLAG IS NULL AND
			VVFORL IS NOT NULL)
			ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000'),VVPCSQ)WHERE ROWNUM=1;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('FRESH SEARCH..!!');

        END;

         ELSIF(TEMP_POD_COUNT_PRIVIOUS=3 AND TEMP_NEXT_PORT_2 IS NOT NULL) THEN

         BEGIN
                          --      INSERT INTO chandra VALUES('6 '||TEMP_POD_TERMINAL);  commit;
            SELECT VVPCAL INTO TEMP_NEXT_PORT_3 FROM (
			SELECT VVPCAL FROM SEALINER.ITP063 WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99 AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))>=(
			SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000') AS ETADATE FROM SEALINER.ITP063 WHERE
			VVSRVC=P_I_V_SERVICE AND
			VVPCAL=TEMP_NEXT_PORT_2 AND
			VVVESS=P_I_V_VESSEL AND
			VVVOYN=P_I_V_VOYAGE AND
			VVTRM1=TEMP_LAST_PORT_TER_2 AND
			--VVPCSQ=PORTSEQUENCE' AND
			VVVERS=99 AND
			OMMISSION_FLAG IS NULL AND
			VVFORL IS NOT NULL )
			AND OMMISSION_FLAG IS NULL
			AND VVFORL IS NOT NULL
			AND (VVPCAL ,VVTRM1) NOT IN
			( SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
			VVSRVC =P_I_V_SERVICE AND
			VVPCAL =TEMP_NEXT_PORT_2 AND
			VVVESS =P_I_V_VESSEL AND
			VVVOYN =P_I_V_VOYAGE AND
			VVTRM1=TEMP_LAST_PORT_TER_2 AND
			--VVPCSQ =PORTSEQUENCE' AND
			VVVERS =99 AND
			OMMISSION_FLAG IS NULL AND
			VVFORL IS NOT NULL)
			ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000'),VVPCSQ)WHERE ROWNUM=1;
        EXCEPTION
        WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('FRESH SEARCH..!!');

        END;

         END IF;
      END LOOP;


 -- INSERT INTO chandra VALUES('7 '||TEMP_POD_TERMINAL);  commit;




       P_O_V_ERROR:='000000';

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
                 IDP10.AYDEST                        DEL_VLS,
                 TO_TERMINAL                         DEPOT_VLS,
                 DISCHARGE_PORT                      POD,
                 TO_TERMINAL                         POD_TERMINAL,
                 TO_TERMINAL                         TERMINAL,
                (SELECT A.PARTNER_VALUE
                  FROM   EDI_TRANSLATION_DETAIL A
                  WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID
                                       FROM   EDI_TRANSLATION_HEADER ETH
                                       WHERE  ETH.STANDARD = 'EDIFACT'
                                              AND ETH.CODE_SET = 'IGMTML')
                         AND A.SEALINER_VALUE = TO_TERMINAL
                         AND ROWNUM < 2)                                     CUST_CODE,
                  (SELECT A.VSCLSN
                  FROM   ITP060 A
                  WHERE  A.VSVESS = P_I_V_VESSEL
                         AND ROWNUM < 2)                                   CALL_SIGN,
                 (SELECT LOYD_NO
                  FROM   ITP060 A
                  WHERE  A.VSVESS = P_I_V_VESSEL
                         AND ROWNUM < 2)                                     IMO_CODE,           --NEED TO PUT
                (SELECT A.PARTNER_VALUE
                  FROM   EDI_TRANSLATION_DETAIL A
                  WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID
                                       FROM   EDI_TRANSLATION_HEADER ETH
                                       WHERE  ETH.STANDARD = 'EDIFACT'
                                              AND ETH.CODE_SET = 'IGMLINECD')
                         AND A.SEALINER_VALUE = TO_TERMINAL
                         AND ROWNUM < 2)                                     LINE_CODE,           --NEED TO PUT
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
                                     VVPCAL = P_I_V_POD
                                            AND (P_I_V_SERVICE IS NULL
                                                    or VVSRVC =P_I_V_SERVICE)
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
                                      WHERE  VVPCAL = P_I_V_POD
                                             AND (P_I_V_SERVICE IS NULL
                                                     or VVSRVC =P_I_V_SERVICE)
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
                  WHERE  ROWNUM = 1)                                        PORT_ORIGIN,        --NEED TO PUT
                 ''                                    PORT_ARRIVAL,      --NEED TO PUT
                    (SELECT A.PARTNER_VALUE
                  FROM   EDI_TRANSLATION_DETAIL A
                  WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID
                                       FROM   EDI_TRANSLATION_HEADER ETH
                                       WHERE  ETH.STANDARD = 'EDIFACT'
                                              AND ETH.CODE_SET = 'AGENT_CODE')
                         AND A.SEALINER_VALUE =P_I_V_POD)                                      AGENT_CODE,
                 TEMP_LAST_PORT_1                              LAST_PORT_1,     --  -3
                TEMP_LAST_PORT_2                              LAST_PORT_2,     --  -2
                TEMP_LAST_PORT_3                              LAST_PORT_3,     --  -1
                 --NEW FIELD
                 TEMP_NEXT_PORT_1                                       NEXT_PORT_4,     --  -1
                 TEMP_NEXT_PORT_2                                       NEXT_PORT_5,     --  -2
                 TEMP_NEXT_PORT_3                                       NEXT_PORT_6,     --  -3
                 -- END
                 'CONTAINERISED'                     VESSEL_TYPE,
                 'CONTAINERS'                        GEN_DESC,
                 ''                                  MASTER_NAME,
                 (SELECT VSCNCD
                  FROM   ITP060 A
                  WHERE  A.VSVESS = P_I_V_VESSEL
                         AND ROWNUM < 2)             VESSEL_NATION,
                 ''                                  IGM_NUMBER,
                 ''                                  IGM_DATE,
                  (SELECT VVAPDT
                  FROM   ITP063 A
                  WHERE  VVPCAL = P_I_V_POD
                         AND (P_I_V_SERVICE IS NULL
                                or VVSRVC =P_I_V_SERVICE)
                         AND VVVESS = P_I_V_VESSEL
                         AND VVVOYN = P_I_V_VOYAGE
                         AND ( P_I_V_POD_TERMINAL IS NULL
                                OR VVTRM1 = P_I_V_POD_TERMINAL )
                         AND A.VVVERS = 99
                         AND A.VVFORL IS NOT NULL
                         AND A.OMMISSION_FLAG IS NULL
                         AND ROWNUM < 2)                                  ARRIVAL_DATE,
                   (SELECT VVAPTM
                  FROM   ITP063 A
                  WHERE  VVPCAL = P_I_V_POD
                         AND (P_I_V_SERVICE IS NULL
                              or VVSRVC =P_I_V_SERVICE)
                         AND VVVESS = P_I_V_VESSEL
                         AND VVVOYN = P_I_V_VOYAGE
                         AND ( P_I_V_POD_TERMINAL IS NULL
                                OR VVTRM1 = P_I_V_POD_TERMINAL )
                         AND A.VVVERS = 99
                         AND A.VVFORL IS NOT NULL
                         AND A.OMMISSION_FLAG IS NULL
                         AND ROWNUM < 2)                                ARRIVAL_TIME,
                  ''                             ARRIVAL_DATE_ATA,
                  ''                             ARRIVAL_TIME_ATA,
                  --NEW MANIFENT FILE CR 14/111/2019
                  ''                        DEP_MANIF_NO,
                  ''                        DEP_MANIFEST_DATE,
                  ''                        SUBMITTER_TYPE,

                   (SELECT PARTNER_VALUE
                    FROM EDI_TRANSLATION_DETAIL

                    WHERE ETH_UID IN (

                    SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                    WHERE CODE_SET='IGMSUBMITC'

                    ) AND SEALINER_VALUE=(select PIOFFC from itp040 WHERE PICODE=P_I_V_POD)  -- fsc
                    AND ROWNUM=1 AND RECORD_STATUS='A')                     SUBMITTER_CODE,
                  (SELECT PARTNER_VALUE
                    FROM EDI_TRANSLATION_DETAIL

                    WHERE ETH_UID IN (

                    SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                    WHERE CODE_SET='IGMAUTHREP'

                    ) AND SEALINER_VALUE=(select PIOFFC from itp040 WHERE PICODE=P_I_V_POD)  -- fsc
                    AND ROWNUM=1 AND RECORD_STATUS='A')                     AUTHORIZ_REP_CODE,
                  ''                        SHIPPING_LINE_BOND_NO_R,
                  ''                        MODE_OF_TRANSPORT,
                  ''                        SHIP_TYPE,
                  ''                        CONVEYANCE_REFERENCE_NO,
                  ''                        TOL_NO_OF_TRANS_EQU_MANIF,
                  ''                        CARGO_DESCRIPTION,
                  ''                        BRIEF_CARGO_DES,
                    (SELECT vvdpdt
                  FROM   ITP063 A
                  WHERE  VVPCAL = P_I_V_POD
                         AND (P_I_V_SERVICE IS NULL
                                or VVSRVC =P_I_V_SERVICE)
                         AND VVVESS = P_I_V_VESSEL
                         AND VVVOYN = P_I_V_VOYAGE
                         AND ( P_I_V_POD_TERMINAL IS NULL
                                OR VVTRM1 = P_I_V_POD_TERMINAL )
                         AND A.VVVERS = 99
                         AND A.VVFORL IS NOT NULL
                         AND A.OMMISSION_FLAG IS NULL
                         AND ROWNUM < 2)                         EXPECTED_DATE,              --NEED TO PUT SUB QURY
                  (SELECT vvdptm
                  FROM   ITP063 A
                  WHERE  VVPCAL = P_I_V_POD
                         AND (P_I_V_SERVICE IS NULL
                              or VVSRVC =P_I_V_SERVICE)
                         AND VVVESS = P_I_V_VESSEL
                         AND VVVOYN = P_I_V_VOYAGE
                         AND ( P_I_V_POD_TERMINAL IS NULL
                                OR VVTRM1 = P_I_V_POD_TERMINAL )
                         AND A.VVVERS = 99
                         AND A.VVFORL IS NOT NULL
                         AND A.OMMISSION_FLAG IS NULL
                         AND ROWNUM < 2)                      TIME_OF_DEPT,                      --NEED TO PUT SUB QURY

                  ''                        TOTAL_NO_OF_TRAN_S_CONT_REPO_ON_ARI_DEP,
                  ''                        MESSAGE_TYPE,
                  'UD'                      VESSEL_TYPE_MOVEMENT,
                    (SELECT PARTNER_VALUE
                    FROM EDI_TRANSLATION_DETAIL

                    WHERE ETH_UID IN (

                    SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                    WHERE CODE_SET='IGMAUTHSEA'

                    ) AND SEALINER_VALUE=(select PIOFFC from itp040 WHERE PICODE=P_I_V_POD)   -- fsc from port
                    AND ROWNUM=1 AND RECORD_STATUS='A')                        AUTHORIZED_SEA_CARRIER_CODE,                 --NEED TO PUT SUB QURY
                  (SELECT PORT_OF_REGISTRY
                  FROM SEALINER.ITP060
                  WHERE VSRCST='A' AND
                  VSVESS= P_I_V_VESSEL)                      PORT_OF_REGISTRY,                                      --NEED TO PUT SUB QURY
                 (select FLAG_EFF_DATE
                  from sealiner.ITP060
                  where VSRCST='A'
                  AND VSVESS= P_I_V_VESSEL)                        REGISTRY_DATE,        --NEED TO PUT SUB QURY
                  'R D'                     VOYAGE_DETAILS,
                  'R D'                     SHIP_ITINERARY_SEQUENCE,
                  'R D'                     SHIP_ITINERARY,
                    (select PINAME from itp040
                  where PIRCST='A'
                  AND PICODE=P_I_V_POD )                   PORT_OF_CALL_NAME,                    --NEED TO PUT SUB QURY
                  'D R'                     ARRIVAL_DEPARTURE_DETAILS,
                  'Q R'                     TOTAL_NO_OF_TRANSPORT_EQUIPMENT_REPORTED_ON_ARRIVAL_DEPARTURE,

                  --END MANIFENT FILE CR 14/111/2019
                  ''                             NEW_VOYAGE,
                  ''                             NEW_VESSEL,
                    (SELECT PARTNER_VALUE
                  FROM EDI_TRANSLATION_DETAIL
                  WHERE ETH_UID IN (
                  SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                  WHERE CODE_SET='IGMTML'
                  ) AND SEALINER_VALUE=TEMP_POD_TERMINAL AND ROWNUM=1)            CUSTOM_TERMINAL_CODE,
                 (SELECT GROSS_TON_INTER
                  FROM   ITP060 A
                  WHERE  A.VSVESS = P_I_V_VESSEL
                         AND ROWNUM < 2)             GROSS_WEIGHT,
                 (SELECT NET_TON_INTER
                  FROM   ITP060 A
                  WHERE  A.VSVESS = IDP5.ACT_VESSEL_CODE
                         AND ROWNUM < 2)                              NET_WEIGHT,
                 '0'                                 TOTAL_BLS,
                 ''                                  LIGHT_DUE,
                 'N'                                 SM_BT_CARGO,
                 'N'                                 SHIP_STR_DECL,
                 ''                                 CREW_LST_DECL,
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
                  (SELECT
                        CASE
                           WHEN BT.TRANSPORT_MODE='T' THEN 'R'
                           WHEN BT.TRANSPORT_MODE='R' THEN 'T'
                        END
                  FROM   IDP005 BT
                  WHERE   BT.VOYAGE_SEQ <> 1
                         AND BT.TRANSPORT_MODE IN ( 'T', 'R' )
                         AND ROWNUM < 2)                                   TRANSPORT_MODE,      --NEED TO PUT SUB QURY
                 ''                                  ROAD_CARR_CODE,
                 ''                                  ROAD_TP_BOND_NO,
                 ''                                  SUBMIT_DATE_TIME,
                 ''                           DPD_MOVEMENT,
                 ''                           DPD_CODE,
               ''                             BL_VERSION,     --NEED TO PUT SUB QURY
               ''                           CUSTOMERS_ADDRESS_1,               --NEED TO PUT SUB QURY
                ''                                 CUSTOMERS_ADDRESS_2,        --NEED TO PUT SUB QURY
                ''                                CUSTOMERS_ADDRESS_3,              --NEED TO PUT SUB QURY
               ''                             CUSTOMERS_ADDRESS_4,             --NEED TO PUT SUB QURY
                 ''                                                      COLOR_FLAG,
                (SELECT
                SUM(EYMTWT)
                FROM IDP055 I
                WHERE
                EYBLNO =SYBLNO )                                    NET_WEIGHT_METRIC,
                 (SELECT SUM (EYPCKG)
                 FROM IDP055 WHERE
                 EYBLNO=SYBLNO )                                          NET_PACKAGE
                 --NEW BL SECTION FIELSD
            --New VesselVoyage-Fields(28-05-2021) by janmejaya
             , (SELECT A.VSCLSN
                  FROM   ITP060 A
                  WHERE  A.VSVESS = P_I_V_VESSEL
                         AND ROWNUM < 2)  VASSELCODE,--IS CALL SIGN / FROM VSL MASTER
              'F'  EDI,--F (by default) by guru
              'P'  NON_EDI,--P (by default) by guru
              ' '  PARENT_VOY,--keep it blank (its in icl vsl page) by guru
              ' '  VIA_VCN,--We will update (by guru)
              ' '  SUB_TERMIL,--keept it blank (its in ICL vsl page) by guru
              ' '  TYPE_TRANSPORT_MEANS,--10-IMO VSL & 11 - NON IMO VSL(by guru)
              ' '  EQUIMENT_TYPE,--CN - CONTAINER ,In local software we need to select from dropdown(by guru)
              ' '  IGM_YEAR,--blank by guru
              ' '  ROTN_NO,--We will update manually by guru
              ' '  ROTN_DATE,--We will update manually by guru
              ' '  JOB_NO,--when scmtr file generate, its generate sequencewise (done by Chandra)by guru
              ' '  JOB_DATE,--the date on which scmtr file generate(bind current date in ui side) by guru
              ' '  POSITION,--keep it blank (its in icl vsl page) by guru
              ' '  EXCHANGE_RATE,--keep it blank (its in icl vsl page) by guru
              ' '  CIGM_NO,-- keep it blank (its in icl vsl page) by guru
              ' '  CIGM_DATE,--keep it blank (its in icl vsl page) by guru
              ' '  SMTP_NO,--keep it blank (its in icl vsl page) by guru
              ' '  SMTP_DATE,--keep it blank (its in icl vsl page) by guru
              ' '  NO_OF_ITEM_IN_PRIOR,--it should be as per line no. in vsl (auto update by sytem) pending need to discussed with Nikhil
              ' '  NO_OF_ITEM_IN_FIL,-- keep it blank (its in icl vsl page) by guru
              ' '  NO_OF_ITEM_IN_SUPPLIMENTARY,-- keep it blank (its in icl vsl page) by guru
              ' '  TOTAL_WEIGHT,-- no mapping given by guru
              ' '  NO_OF_PASSENGER,--We will update manually (by guru)
              ' '  NO_OF_CREW,--We will update manually(by guru)
              ' '  REMARK_VESSEL-- no mapping given by guru




          FROM   IDP005 IDP5
                 INNER JOIN IDP010 IDP10
                         ON IDP5.SYBLNO = IDP10.AYBLNO
          WHERE   IDP5.DISCHARGE_PORT = P_I_V_POD
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
                 AND ( IDP10.AYIMST <> 9)
                 AND ( P_I_V_BL_STATUS IS NULL
                        OR IDP10.AYIMST = P_I_V_BL_STATUS)
                 AND ( P_I_V_POL IS NULL
                        OR IDP5.LOAD_PORT = P_I_V_POL )
                 AND ( P_I_V_POL_TERMINAL IS NULL
                        OR IDP5.FROM_TERMINAL = P_I_V_POL_TERMINAL )
                 AND ( P_I_V_DEL IS NULL
                        OR IDP10.AYDEST = P_I_V_DEL )
                 AND ( P_I_V_DEPOT IS NULL
                        OR IDP5.TO_TERMINAL = P_I_V_DEPOT )

                 AND IDP10.AYSORC = 'C' ;


      END RCL_IGM_VESSLE_VOYAGE_GET_DATA;

         PROCEDURE RCL_IGM_BL_GET_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR,
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
                             P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                             P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                             P_I_V_POL_TERMINAL VARCHAR2 DEFAULT NULL,
                             P_I_V_DIRECTION    VARCHAR2 DEFAULT NULL,
                             P_O_V_ERROR        OUT VARCHAR2)
  IS
    BL_COUNT NUMBER(20);
    SERVISE_COUNT NUMBER(20);
    TEMP_POD_COUNT_PRIVIOUS NUMBER(2);
    TEMP_POD_COUNT_NEXT NUMBER(4);
    TEMP_LAST_PORT_1  VARCHAR2(20);     --LAST_PORT_1
    TEMP_LAST_PORT_TER_1  VARCHAR2(20);
    TEMP_LAST_PORT_2  VARCHAR2(20);     --LAST_PORT_2
    TEMP_LAST_PORT_TER_2  VARCHAR2(20);
    TEMP_LAST_PORT_3  VARCHAR2(20);     --LAST_PORT_3
    TEMP_LAST_PORT_TER_3 VARCHAR2(20);
    TEMP_NEXT_PORT_1  VARCHAR2(20);     -- FOR 1ST NEXT PORT  NEXT_PORT_4
    TEMP_NEXT_PORT_2  VARCHAR2(20);     -- FOR 2ND NEXT PORT  NEXT_PORT_5
    TEMP_NEXT_PORT_3  VARCHAR2(20);     -- FOR 3RD NEXT PORT  NEXT_PORT_6
    TEMP_SERVICE VARCHAR2(20);
    TEMP_POD_TERMINAL VARCHAR2(20);
    V_SQL   VARCHAR2(6000);
    V_SQL_CNDTN  VARCHAR2(4000);
  BEGIN

       P_O_V_ERROR:='000000';

         OPEN P_O_REFIGMTABFIND FOR
          SELECT SYBLNO                              BL_NO,
                 IDP10.AYISDT                        BL_DATE,
                 ACT_SERVICE_CODE                    SERVICE,
                 ACT_VESSEL_CODE                     VESSEL,
                 ACT_VOYAGE_NUMBER                   VOYAGE,
                 ACT_PORT_DIRECTION                  DIRECTION,
                 FROM_TERMINAL                       POL_TERMINAL,
                 IDP10.AYDEST                        DEL_VLS,
                 TO_TERMINAL                         DEPOT_VLS,
                 DISCHARGE_PORT                      POD,
                 TO_TERMINAL                         POD_TERMINAL,
                  (SELECT MA_SEQ_NO
                  FROM   RCLTBLS.DEX_BL_HEADER A
                  WHERE  DN_POD = P_I_V_POD
                         AND DISCHARGE_VESSEL = P_I_V_VESSEL
                         AND DISCHARGE_VOYAGE = P_I_V_VOYAGE
                         AND PK_BL_NO = IDP5.SYBLNO
                         AND ROWNUM < 2)             BL_VERSION,
                 --NEW BL SECTION FIELSD
                ''															    CONSOLIDATED_INDICATOR,
                ''															    PREVIOUS_DECLARATION,
                ''															    CONSOLIDATOR_PAN,
                ''															    CIN_TYPE,
                'UD'															    MCIN,
                ''															    CSN_SUBMITTED_TYPE,
                ''															    CSN_SUBMITTED_BY,
                ''                                                              CSN_REPORTING_TYPE,
                ''															    CSN_SITE_ID,
                ''															    CSN_NUMBER,
                ''                                                              CSN_DATE,
                ''                                                              PREVIOUS_MCIN,
                ''                                                              SPLIT_INDICATOR,
                ''                                                              ITEM_NUMBER,

                 CASE
                   WHEN AYMPOD = AYDEST THEN 'LC'
                   ELSE 'TI'
                 END                                                             CARGO_MOVMNT,


                (SELECT SUM (EYPCKG)
                 FROM IDP055 WHERE
                 EYBLNO=SYBLNO AND ROWNUM=1)                                                 NUMBER_OF_PACKAGES, --NEED TO DISCUSS
                (SELECT PARTNER_VALUE
                FROM SEALINER.EDI_TRANSLATION_HEADER HDR,
                SEALINER.EDI_TRANSLATION_DETAIL DTL,
                RCLTBLS.DEX_BL_COMMODITY BL
                WHERE  HDR.CODE_SET='IGMPKGKIND'
                AND HDR.ETH_UID=DTL.ETH_UID
                AND DN_PACKAGE_KIND=SEALINER_VALUE
                AND BL.FK_BL_NO= SYBLNO  AND ROWNUM=1)                          TYPE_OF_PACKAGE,
                (SELECT PARTNER_VALUE FROM SEALINER.EDI_TRANSLATION_HEADER HDR
                ,SEALINER.EDI_TRANSLATION_DETAIL DTL
                WHERE  HDR.CODE_SET='IGMPORT' AND HDR.ETH_UID=DTL.ETH_UID
                AND  SEALINER_VALUE=P_I_V_POD AND ROWNUM=1)                                  FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE,
                ''                                                              TYPE_OF_CARGO,
                ''                                                              SPLIT_INDICATOR_LIST,
                (SELECT  DN_POL     FROM RCLTBLS.DEX_BL_HEADER
                WHERE PK_BL_NO=SYBLNO AND ROWNUM=1)                                          PORT_OF_ACCEPTANCE,
               (SELECT PARTNER_VALUE
                  FROM EDI_TRANSLATION_DETAIL
                WHERE ETH_UID IN (
                SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                                  WHERE CODE_SET='IGMDEL'
                  ) AND SEALINER_VALUE= TO_TERMINAL  AND ROWNUM=1 AND RECORD_STATUS='A')                                        PORT_OF_RECEIPT,
                ''                                                              UCR_TYPE,
                ''                                                              UCR_CODE,

              --  ''                                                              EQUIPMENT_SEAL_TYPE,

                ''                                                              PORT_OF_ACCEPTANCE_NAME,
                (SELECT TQTRNM DEPOT_NAME
                FROM ITP130 WHERE TQTERM=TO_TERMINAL-- DEL FROM BL
                AND    TQRCST='A')                                                PORT_OF_RECEIPT_NAME,
                (SELECT FEDERAL_TAX_ID PAN FROM SEALINER.ITP010
                WHERE CURCST='A'
                AND CUCUST IN (SELECT FK_CUSTOMER_CODE
                FROM RCLTBLS.DEX_BL_CUSTOMERS
                WHERE CUSTOMER_TYPE IN('N','1','2','3')
                AND FK_BL_NO =SYBLNO AND ROWNUM=1)AND ROWNUM=1 )                     PAN_OF_NOTIFIED_PARTY,
                (SELECT STMTWT FROM SEALINER.ITP0TD WHERE SGTRAD='*')               UNIT_OF_WEIGHT,
                'GV'                                                                GROSS_VOLUME,
                'UOV'                                                               UNIT_OF_VOLUME,
                'CISN'                                                              CARGO_ITEM_SEQUENCE_NO,
                (SELECT BYRMKS FROM IDP050 WHERE BYBLNO=SYBLNO AND ROWNUM=1)                     CARGO_ITEM_DESCRIPTION,
                'NOPH'                                                              NUMBER_OF_PACKAGES_HID,
                (SELECT PARTNER_VALUE
                FROM SEALINER.EDI_TRANSLATION_HEADER HDR,
                SEALINER.EDI_TRANSLATION_DETAIL DTL,
                RCLTBLS.DEX_BL_COMMODITY BL
                WHERE  HDR.CODE_SET='IGMPKGKIND'
                AND HDR.ETH_UID=DTL.ETH_UID
                AND DN_PACKAGE_KIND=SEALINER_VALUE
                AND BL.FK_BL_NO= SYBLNO AND ROWNUM=1)                              TYPE_OF_PACKAGES_HID,
                'PORTOCSN'                                                          PORT_OF_CALL_SEQUENCE_NUMBER,
                (SELECT VVPCAL FROM (
                SELECT VVPCAL FROM SEALINER.ITP063
                WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99
                AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))<=(
                SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000')
                AS ETADATE FROM SEALINER.ITP063 WHERE
                VVSRVC=ACT_SERVICE_CODE AND
                VVPCAL=P_I_V_POD AND
                VVVESS=P_I_V_VESSEL AND
                VVVOYN=P_I_V_VOYAGE AND
                --VVPCSQ=PORTSEQUENCE' AND
                VVVERS=99 AND
                OMMISSION_FLAG IS NULL AND
                VVFORL IS NOT NULL )
                AND OMMISSION_FLAG IS NULL
                AND VVFORL IS NOT NULL
                AND (VVPCAL ,VVTRM1) NOT IN
                (SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
                VVSRVC=ACT_SERVICE_CODE AND
                VVPCAL=P_I_V_POD AND
                VVVESS=P_I_V_VESSEL AND
                VVVOYN=P_I_V_VOYAGE AND
                VVTRM1=TO_TERMINAL AND
                --VVPCSQ=PORTSEQUENCE' AND
                VVVERS=99 AND
                OMMISSION_FLAG IS NULL AND
                VVFORL IS NOT NULL)
                ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000')
                DESC,VVPCSQ DESC)WHERE ROWNUM=1)                            PORT_OF_CALL_CODED,
                (SELECT VVPCAL FROM (
                SELECT VVPCAL FROM SEALINER.ITP063 WHERE
                VVVESS = P_I_V_VESSEL AND VVVERS= 99
                AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))>=(
                SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000')
                AS ETADATE FROM SEALINER.ITP063 WHERE
                VVSRVC=ACT_SERVICE_CODE AND
                VVPCAL=P_I_V_POD AND
                VVVESS=P_I_V_VESSEL AND
                VVVOYN=P_I_V_VOYAGE AND
                --VVPCSQ=PORTSEQUENCE' AND
                VVVERS=99 AND
                OMMISSION_FLAG IS NULL AND
                VVFORL IS NOT NULL )
                AND OMMISSION_FLAG IS NULL
                AND VVFORL IS NOT NULL
                AND (VVPCAL ,VVTRM1) NOT IN
                ( SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
                VVSRVC =ACT_SERVICE_CODE AND
                VVPCAL =P_I_V_POD AND
                VVVESS =P_I_V_VESSEL AND
                VVVOYN =P_I_V_VOYAGE AND
                VVTRM1=TO_TERMINAL AND
                --VVPCSQ =PORTSEQUENCE' AND
                VVVERS =99 AND
                OMMISSION_FLAG IS NULL AND
                VVFORL IS NOT NULL)
                ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000'),VVPCSQ)
                WHERE ROWNUM=1)                                                   NEXT_PORT_OF_CALL_CODED,
                'MCLC'                                                              MC_LOCATION_CUSTOMS,
                (SELECT  FK_UNNO  FROM  RCLTBLS.DEX_BL_SPECIAL_CARGO_DTL SP
                , RCLTBLS.DEX_BL_COMMODITY CM WHERE CM.DN_SPECIAL_HNDL='D1'
                AND CM.FK_BL_NO=SP.FK_BL_NO
                AND CM.COMMODITY_SEQ=SP.COMMODITY_SEQ
                AND ROWNUM=1  AND CM.FK_BL_NO=SYBLNO AND ROWNUM=1)                               UNO_CODE,
                (SELECT  FK_IMO_CLASS  FROM  RCLTBLS.DEX_BL_SPECIAL_CARGO_DTL SP
                , RCLTBLS.DEX_BL_COMMODITY CM WHERE CM.DN_SPECIAL_HNDL='D1'
                AND CM.FK_BL_NO=SP.FK_BL_NO
                AND CM.COMMODITY_SEQ=SP.COMMODITY_SEQ
                AND ROWNUM=1 AND CM.FK_BL_NO= SYBLNO AND ROWNUM=1)                               IMDG_CODE,
                ''                                                                  CONTAINER_WEIGHT,
                 --END
                 ''                                  NHAVA_SHEVA_ETA,
                 FINAL_PLACE_OF_DELIVERY_NAME        FINAL_PLACE_DELIVERY,
                 ''                                  PACKAGES,
                 ''                                  CFS_NAME,
                 ''                                  MBL_NO,
                 (SELECT FK_HOUSE_BL_NO  FROM RCLTBLS.DEX_BL_HEADER
                 WHERE FK_BOOKING_NO IN (
                 SELECT FK_BOOKING_NO FROM
                 RCLTBLS.DEX_BL_HEADER WHERE PK_BL_NO= SYBLNO
                 ) AND FK_HOUSE_BL_NO IS NOT NULL)                                  HBL_NO,
                 ''                                  FROM_ITEM_NO,
                 ''                                  TO_ITEM_NO,
                 ''                                  SRL_NO
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
                 AND ( IDP10.AYIMST <> 9)
                 AND ( P_I_V_BL_STATUS IS NULL
                        OR IDP10.AYIMST = P_I_V_BL_STATUS)
                 AND ( P_I_V_POL IS NULL
                        OR IDP5.LOAD_PORT = P_I_V_POL )
                 AND ( P_I_V_POL_TERMINAL IS NULL
                        OR IDP5.FROM_TERMINAL = P_I_V_POL_TERMINAL )
                 AND ( P_I_V_DEL IS NULL
                        OR IDP10.AYDEST = P_I_V_DEL )
                 AND ( P_I_V_DEPOT IS NULL
                        OR IDP5.TO_TERMINAL = P_I_V_DEPOT )

                 AND IDP10.AYSORC = 'C' ;


      END RCL_IGM_BL_GET_DATA;



      PROCEDURE RCL_IGM_SAVE_DATA (
                                P_I_V_BL                   VARCHAR2 DEFAULT NULL,
                                P_I_V_SERVICE              VARCHAR2 DEFAULT NULL,
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
                                P_I_V_DEL                  VARCHAR2 DEFAULT NULL,
                                P_I_V_DEPOT                VARCHAR2 DEFAULT NULL,
                                P_I_V_BL_STATUS            VARCHAR2 DEFAULT NULL,
                                P_I_V_ADD_USER             VARCHAR2 DEFAULT NULL,
                                P_I_V_ADD_DATE             VARCHAR2 DEFAULT NULL,
                                P_I_V_CHANGE_USER          VARCHAR2 DEFAULT NULL,
                                P_I_V_CHANGE_DATE          VARCHAR2 DEFAULT NULL,
                                P_I_V_DPD_CODE             VARCHAR2 DEFAULT NULL,
                                P_I_V_DPD_MOVEMENT         VARCHAR2 DEFAULT NULL,
                                P_I_V_BL_VEERSION          VARCHAR2 DEFAULT NULL,
                                P_I_V_CUSTOM_TERMINAL_CODE VARCHAR2 DEFAULT NULL,
                                P_I_V_CUSTOM_ADD1          VARCHAR2 DEFAULT NULL,
                                P_I_V_CUSTOM_ADD2          VARCHAR2 DEFAULT NULL,
                                P_I_V_CUSTOM_ADD3          VARCHAR2 DEFAULT NULL,
                                P_I_V_CUSTOM_ADD4          VARCHAR2 DEFAULT NULL,
                                P_I_V_IS_PRESENT_IN_DB     VARCHAR2 DEFAULT NULL,
                                P_I_V_IS_SELECTED          VARCHAR2 DEFAULT NULL,
                                P_I_V_COLOR_FLAG            VARCHAR2 DEFAULT NULL,
                                P_I_V_PACKAGE_BL_LEVEL      VARCHAR2 DEFAULT NULL,
                                P_I_V_GROSS_CARGO_WEIGHT_BL_LEVEL   VARCHAR2 DEFAULT NULL,

                                --  P_I_V_CONTAINER_DTLS          VARCHAR2 DEFAULT NULL,

                                P_I_V_CONSIGNEE_ADD1        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_ADD2        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_ADD3        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_ADD4        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_COUNTRYCODE     VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_CODE        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_NAME        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_STATE       VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_ZIP         VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNEE_CITY        VARCHAR2 DEFAULT NULL,

                                P_I_V_MARKSNUMBER_DESCRIPTION     VARCHAR2 DEFAULT NULL,
                                P_I_V_MARKSNUMBER_MARKS        VARCHAR2 DEFAULT NULL,

                                P_I_V_NOTIFYPARTY_ADD1        VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_ADD2        VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_ADD3        VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_ADD4        VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_CITY        VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_COUNTRYCODE        VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_CODE        VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_NAME        VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_STATE       VARCHAR2 DEFAULT NULL,
                                P_I_V_NOTIFYPARTY_ZIP         VARCHAR2 DEFAULT NULL,

                                P_I_V_CONSIGNER_ADD1		VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_ADD2		VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_ADD3		VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_ADD4		VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_COUNTRYCODE		VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_CODE		VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_NAME		VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_STATE		VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_ZIP			VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSIGNER_CITY		VARCHAR2 DEFAULT NULL,
                                --NEW VESSEL/VOYAGE SECTION 10-12-19

                                P_I_V_NEXT_PORT_1            VARCHAR2 DEFAULT NULL,
                                P_I_V_NEXT_PORT_2        VARCHAR2 DEFAULT NULL,
                                P_I_V_NEXT_PORT_3        VARCHAR2 DEFAULT NULL,

                                P_I_V_DEP_MANIF_NO        VARCHAR2 DEFAULT NULL,
                                P_I_V_DEP_MANIFEST_DATE        VARCHAR2 DEFAULT NULL,
                                P_I_V_SUBMITTER_TYPE        VARCHAR2 DEFAULT NULL,
                                P_I_V_SUBMITTER_CODE        VARCHAR2 DEFAULT NULL,
                                P_I_V_AUTHORIZ_REP_CODE        VARCHAR2 DEFAULT NULL,
                                P_I_V_SHIPPING_LINE_BOND_NO_R        VARCHAR2 DEFAULT NULL,
                                P_I_V_MODE_OF_TRANSPORT        VARCHAR2 DEFAULT NULL,
                                P_I_V_SHIP_TYPE        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONVEYANCE_REFERENCE_NO       VARCHAR2 DEFAULT NULL,
                                P_I_V_CARGO_DESCRIPTION         VARCHAR2 DEFAULT NULL,
                                P_I_V_TOL_NO_OF_TRANS_EQU_MANIF        VARCHAR2 DEFAULT NULL,
                                P_I_V_BRIEF_CARGO_DES        VARCHAR2 DEFAULT NULL,
                                P_I_V_EXPECTED_DATE        VARCHAR2 DEFAULT NULL,
                                P_I_V_TIME_OF_DEPT        VARCHAR2 DEFAULT NULL,
                                P_I_V_PORT_OF_CALL_COD        VARCHAR2 DEFAULT NULL,
                                P_I_V_TOTAL_NO_OF_TRAN_S_CONT_REPO_ON_ARI_DEP        VARCHAR2 DEFAULT NULL,
                                P_I_V_MESSAGE_TYPE        VARCHAR2 DEFAULT NULL,
                                P_I_V_VESSEL_TYPE_MOVEMENT        VARCHAR2 DEFAULT NULL,
                                P_I_V_AUTHORIZED_SEA_CARRIER_CODE       VARCHAR2 DEFAULT NULL,
                                P_I_V_PORT_OF_REGISTRY         VARCHAR2 DEFAULT NULL,
                                P_I_V_REGISTRY_DATE        VARCHAR2 DEFAULT NULL,
                                P_I_V_VOYAGE_DETAILS        VARCHAR2 DEFAULT NULL,
                                P_I_V_SHIP_ITINERARY_SEQUENCE        VARCHAR2 DEFAULT NULL,
                                P_I_V_SHIP_ITINERARY        VARCHAR2 DEFAULT NULL,
                                P_I_V_PORT_OF_CALL_NAME        VARCHAR2 DEFAULT NULL,
                                P_I_V_ARRIVAL_DEPARTURE_DETAILS        VARCHAR2 DEFAULT NULL,
                                P_I_V_TOTAL_NO_OF_TRANSPORT_EQUIPMENT_REPORTED_ON_ARRIVAL_DEPARTURE        VARCHAR2 DEFAULT NULL,

                                --VESSEL/VOYAGE SECTION END
                                --NEW BL SECTION 10-12-19

                                P_I_V_CONSOLIDATED_INDICATOR        VARCHAR2 DEFAULT NULL,
                                P_I_V_PREVIOUS_DECLARATION        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONSOLIDATOR_PAN        VARCHAR2 DEFAULT NULL,
                                P_I_V_CIN_TYPE        VARCHAR2 DEFAULT NULL,
                                P_I_V_MCIN        VARCHAR2 DEFAULT NULL,
                                P_I_V_CSN_SUBMITTED_TYPE        VARCHAR2 DEFAULT NULL,
                                P_I_V_CSN_SUBMITTED_BY        VARCHAR2 DEFAULT NULL,
                                P_I_V_CSN_REPORTING_TYPE        VARCHAR2 DEFAULT NULL,
                                P_I_V_CSN_SITE_ID       VARCHAR2 DEFAULT NULL,
                                P_I_V_CSN_NUMBER         VARCHAR2 DEFAULT NULL,
                                P_I_V_CSN_DATE        VARCHAR2 DEFAULT NULL,
                                P_I_V_PREVIOUS_MCIN        VARCHAR2 DEFAULT NULL,
                                P_I_V_SPLIT_INDICATOR        VARCHAR2 DEFAULT NULL,
                                P_I_V_NUMBER_OF_PACKAGES        VARCHAR2 DEFAULT NULL,
                                P_I_V_TYPE_OF_PACKAGE        VARCHAR2 DEFAULT NULL,
                                P_I_V_FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE        VARCHAR2 DEFAULT NULL,
                                P_I_V_TYPE_OF_CARGO        VARCHAR2 DEFAULT NULL,
                                P_I_V_SPLIT_INDICATOR_LIST        VARCHAR2 DEFAULT NULL,
                                P_I_V_PORT_OF_ACCEPTANCE       VARCHAR2 DEFAULT NULL,
                                P_I_V_PORT_OF_RECEIPT         VARCHAR2 DEFAULT NULL,
                                P_I_V_UCR_TYPE        VARCHAR2 DEFAULT NULL,
                                P_I_V_UCR_CODE        VARCHAR2 DEFAULT NULL,

                                -- P_I_V_EQUIPMENT_SEAL_TYPE        VARCHAR2 DEFAULT NULL,

                                P_I_V_PORT_OF_ACCEPTANCE_NAME        VARCHAR2 DEFAULT NULL,
                                P_I_V_PORT_OF_RECEIPT_NAME        VARCHAR2 DEFAULT NULL,
                                P_I_V_PAN_OF_NOTIFIED_PARTY        VARCHAR2 DEFAULT NULL,
                                P_I_V_UNIT_OF_WEIGHT        VARCHAR2 DEFAULT NULL,
                                P_I_V_GROSS_VOLUME        VARCHAR2 DEFAULT NULL,
                                P_I_V_UNIT_OF_VOLUME        VARCHAR2 DEFAULT NULL,
                                P_I_V_CARGO_ITEM_SEQUENCE_NO        VARCHAR2 DEFAULT NULL,
                                P_I_V_CARGO_ITEM_DESCRIPTION        VARCHAR2 DEFAULT NULL,
                                P_I_V_CONTAINER_WEIGHT       VARCHAR2 DEFAULT NULL,
                                P_I_V_NUMBER_OF_PACKAGES_HID         VARCHAR2 DEFAULT NULL,
                                P_I_V_TYPE_OF_PACKAGES_HID        VARCHAR2 DEFAULT NULL,
                                P_I_V_PORT_OF_CALL_SEQUENCE_NUMBER        VARCHAR2 DEFAULT NULL,
                                P_I_V_PORT_OF_CALL_CODED        VARCHAR2 DEFAULT NULL,
                                P_I_V_NEXT_PORT_OF_CALL_CODED        VARCHAR2 DEFAULT NULL,
                                P_I_V_MC_LOCATION_CUSTOMS        VARCHAR2 DEFAULT NULL,
                                P_I_V_UNO_CODE        VARCHAR2 DEFAULT NULL,
                                P_I_V_IMDG_CODE        VARCHAR2 DEFAULT NULL,

                                --BL SECTION END

                                P_O_V_ERROR                OUT VARCHAR2)
  IS
                                V_SRL_NO NUMBER;
                                 V_CNT NUMBER;
                                  SEQ_NUMBER   NUMBER(10);



  BEGIN

      --INSERT INTO CHANDRA VALUES('CCCC     '||P_I_V_BL_NO); COMMIT;
      P_O_V_ERROR := '000000';
      SELECT IGM_SEQ_NO.NEXTVAL INTO SEQ_NUMBER   FROM  DUAL;



    -- INSERT INTO CHANDRA VALUES('P_I_V_SERVICE                   '||P_I_V_SERVICE              ); COMMIT;
     -- INSERT INTO CHANDRA VALUES('P_I_V_VESSEL                 '||P_I_V_VESSEL            ); COMMIT;


     INSERT INTO VASAPPS.IGM_BL_DETAILS_TWO (
                    BL_NO_PK,
                     SERVICE  ,
                     VESSEL ,
                     VOYAGE   ,
                     POL_TERMINAL ,
                     POD  ,
                    CONSOLIDATED_INDICATOR,
                    PREVIOUS_DECLARATION,
                    CONSOLIDATOR_PAN,
                    CIN_TYPE,
                    MCIN,
                    CSN_SUBMITTED_TYPE,
                    CSN_SUBMITTED_BY,
                    CSN_REPORTING_TYPE,
                    CSN_SITE_ID,
                    CSN_NUMBER,
                    CSN_DATE,
                    PREVIOUS_MCIN,
                    SPLIT_INDICATOR,
                    NUMBER_OF_PACKAGES,
                    TYPE_OF_PACKAGE,
                    FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE,
                    TYPE_OF_CARGO,
                    SPLIT_INDICATOR_LIST,
                    PORT_OF_ACCEPTANCE,
                    PORT_OF_RECEIPT,
                    UCR_TYPE,
                    UCR_CODE,
                    PORT_OF_ACCEPTANCE_NAME,
                    PORT_OF_RECEIPT_NAME,
                    PAN_OF_NOTIFIED_PARTY,
                    UNIT_OF_WEIGHT,
                    GROSS_VOLUME,
                    UNIT_OF_VOLUME,
                    CARGO_ITEM_SEQUENCE_NO,
                    CARGO_ITEM_DESCRIPTION,
                    CONTAINER_WEIGHT,
                    NUMBER_OF_PACKAGES_HID,
                    TYPE_OF_PACKAGES_HID,
                    PORT_OF_CALL_SEQUENCE_NUMBER,
                    PORT_OF_CALL_CODED,
                    NEXT_PORT_OF_CALL_CODED,
                    MC_LOCATION_CUSTOMS,
                    UNO_CODE,
                    IMDG_CODE
) VALUES (
                    P_I_V_BL_NO,                      -- P_I_V_BL_NO,
                     P_I_V_SERVICE  ,
                     P_I_V_VESSEL ,
                     P_I_V_VOYAGE   ,
                     P_I_V_PORT ,
                     P_I_V_TERMINAL  ,

                    P_I_V_CONSOLIDATED_INDICATOR,
                    P_I_V_PREVIOUS_DECLARATION,
                    P_I_V_CONSOLIDATOR_PAN,
                    P_I_V_CIN_TYPE,
                    P_I_V_MCIN,
                    P_I_V_CSN_SUBMITTED_TYPE,
                    P_I_V_CSN_SUBMITTED_BY,
                    P_I_V_CSN_REPORTING_TYPE,
                    P_I_V_CSN_SITE_ID,
                    P_I_V_CSN_NUMBER,
                    P_I_V_CSN_DATE,
                    P_I_V_PREVIOUS_MCIN,
                    P_I_V_SPLIT_INDICATOR,
                    P_I_V_NUMBER_OF_PACKAGES,
                    P_I_V_TYPE_OF_PACKAGE,
                    P_I_V_FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE,
                    P_I_V_TYPE_OF_CARGO,
                    P_I_V_SPLIT_INDICATOR_LIST,
                    P_I_V_PORT_OF_ACCEPTANCE,
                    P_I_V_PORT_OF_RECEIPT,
                    P_I_V_UCR_TYPE,
                    P_I_V_UCR_CODE,
                    P_I_V_PORT_OF_ACCEPTANCE_NAME,
                    P_I_V_PORT_OF_RECEIPT_NAME,
                    P_I_V_PAN_OF_NOTIFIED_PARTY,
                    P_I_V_UNIT_OF_WEIGHT,
                    P_I_V_GROSS_VOLUME,
                    P_I_V_UNIT_OF_VOLUME,
                    P_I_V_CARGO_ITEM_SEQUENCE_NO,
                    P_I_V_CARGO_ITEM_DESCRIPTION,
                    P_I_V_CONTAINER_WEIGHT,
                    P_I_V_NUMBER_OF_PACKAGES_HID,
                    P_I_V_TYPE_OF_PACKAGES_HID,
                    P_I_V_PORT_OF_CALL_SEQUENCE_NUMBER,
                    P_I_V_PORT_OF_CALL_CODED,
                    P_I_V_NEXT_PORT_OF_CALL_CODED,
                    P_I_V_MC_LOCATION_CUSTOMS,
                    P_I_V_UNO_CODE,
                    P_I_V_IMDG_CODE
);


    -- INSERT INTO CHANDRA VALUES('one     '||P_I_V_BL_NO); COMMIT;

/*
  INSERT INTO VASAPPS.IGM_VESSEL_VOYAGE_DETAILES (
                    VESSEL_VOYAGE_PK,
                    FK_BL_NO,
                    SERVICE,
                    POD,
                    POD_TERMINAL,
                    VESSEL,
                    VOYAGE,
                    CUST_CODE,
                    CALL_SIGN,
                    LINE_CODE,
                    AGENT_CODE,
                    PORT_ORIGIN,
                    PORT_ARRIVAL,
                    LAST_PORT_1,
                    LAST_PORT_2,
                    LAST_PORT_3,
                    NEXT_PORT_4,
                    NEXT_PORT_5,
                    NEXT_PORT_6,
                    TERMINAL,
                    VESSEL_TYPE,
                    GEN_DESC,
                    MASTER_NAME,
                    VESSEL_NATION,
                    IGM_NUMBER,
                    IGM_DATE,
                    ARRIVAL_DATE,
                    ARRIVAL_TIME,
                    ARRIVAL_DATE_ATA,
                    ARRIVAL_TIME_ATA,
                    TOTAL_BLS,
                    SM_BT_CARGO,
                    SHIP_STR_DECL,
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
                    NEW_VOYAGE,
                    NEW_VESSEL,
                    SUBMIT_DATE_TIME,
                    NHAVA_SHEVA_ETA,
                    FINAL_PLACE_DELIVERY,
                    PACKAGES,
                    CFS_NAME,
                    MBL_NO,
                    HBL_NO,
                    FROM_ITEM_NO,
                    TO_ITEM_NO,
                    NET_WEIGHT,
                    GROSS_WEIGHT,
                    IMO_CODE,
                    BL_DATE,
                    BL_STATUS,
                    POL,
                    CUSTOM_TERMINAL_CODE,
                    BL_VERSION,
                    DPD_CODE,
                    DPD_MOVEMENT,
                    POL_TERMINAL,
                    CARGO_DECL,
                    CREW_LST_DECL,
                    PASSNGR_LIST,
                    CUSTOMERS_ADDRESS_1,
                    CUSTOMERS_ADDRESS_2,
                    CUSTOMERS_ADDRESS_3,
                    CUSTOMERS_ADDRESS_4,
                    COLOR_FLAG,
                    NET_WEIGHT_METRIC,
                    NET_PACKAGE,
                    DEL_VLS,
                    DEPOT_VLS,
                    DEP_MANIF_NO,
                    DEP_MANIFEST_DATE,
                    SUBMITTER_TYPE,
                    SUBMITTER_CODE,
                    AUTHORIZ_REP_CODE,
                    SHIPPING_LINE_BOND_NO_R,
                    MODE_OF_TRANSPORT,
                    SHIP_TYPE,
                    CONVEYANCE_REFERENCE_NO,
                    CARGO_DESCRIPTION,
                    TOL_NO_OF_TRANS_EQU_MANIF,
                    BRIEF_CARGO_DES,
                    EXPECTED_DATE,
                    TIME_OF_DEPT,
                    TOTAL_NO_OF_TRAN_S_CONT_REPO_ON_ARI_DEP,
                    MESSAGE_TYPE,
                    VESSEL_TYPE_MOVEMENT,
                    AUTHORIZED_SEA_CARRIER_CODE,
                    PORT_OF_REGISTRY,
                    REGISTRY_DATE,
                    VOYAGE_DETAILS,
                    SHIP_ITINERARY_SEQUENCE,
                    SHIP_ITINERARY,
                    PORT_OF_CALL_NAME,
                    ARRIVAL_DEPARTURE_DETAILS,
                    TOTAL_NO_OF_TRANSPORT_EQUIPMENT_REPORTED_ON_ARRIVAL_DEPARTURE
) VALUES (
                SEQ_NUMBER,
                 SEQ_NUMBER,                    --P_I_V_BL,
                P_I_V_SERVICE,
                P_I_V_PORT,
                P_I_V_TERMINAL,
                P_I_V_VESSEL,
                P_I_V_VOYAGE,
                P_I_V_CUST_CODE,
                P_I_V_CALL_SIGN,
                P_I_V_LINE_CODE,
                P_I_V_AGENT_CODE,
                P_I_V_PORT_ORIGIN,
                P_I_V_PORT_ARRIVAL,
                P_I_V_LAST_PORT_1,
                P_I_V_LAST_PORT_2,
                P_I_V_LAST_PORT_3,
                P_I_V_NEXT_PORT_1,
                P_I_V_NEXT_PORT_2,
                P_I_V_NEXT_PORT_3,
                P_I_V_TERMINAL,
                P_I_V_VESSEL_TYPE_MOVEMENT,
                P_I_V_GEN_DESC,
                P_I_V_MASTER_NAME,
                P_I_V_VESSEL_NATION,
                P_I_V_IGM_NUMBER,
                P_I_V_IGM_DATE,
                P_I_V_ARRIVAL_DATE_ETA,
                P_I_V_ARRIVAL_TIME_ETA,
                P_I_V_ARRIVAL_DATE_ATA,
                P_I_V_ARRIVAL_TIME_ATA,
                P_I_V_TOTAL_BLS,
                P_I_V_SM_BT_CARGO,
                P_I_V_SHIP_STR_DECL,
                P_I_V_CREW_EFFECT,
                P_I_V_MARITIME_DECL,
                P_I_V_ITEM_NUMBER,
                P_I_V_CARGO_NATURE,
                P_I_V_CARGO_MOVMNT,
                P_I_V_ITEM_TYPE,
                P_I_V_CARGO_MOVMNT_TYPE,
                P_I_V_TRANSPORT_MODE,
                P_I_V_ROAD_CARR_CODE,
                P_I_V_ROAD_TP_BOND_NO,
                P_I_V_NEW_VESSEL,
                P_I_V_NEW_VOYAGE,
                P_I_V_SUBMIT_DATE_TIME,
                P_I_V_NHAVA_SHEVA_ETA,
                P_I_V_FINAL_PLACE_DELIVERY,
                P_I_V_PACKAGES,
                P_I_V_CFS_NAME,
                P_I_V_MBL_NO,
                P_I_V_HBL_NO,
                P_I_V_FROM_ITEM_NO,
                P_I_V_TO_ITEM_NO,
                TO_NUMBER(P_I_V_NET_WEIGHT),
                TO_NUMBER(P_I_V_GROSS_WEIGHT),
                P_I_V_IMO_CODE,
                P_I_V_BL_DATE,
                P_I_V_BL_STATUS,
                P_I_V_POL,
                P_I_V_CUSTOM_TERMINAL_CODE,
                P_I_V_BL_VEERSION,
                P_I_V_DPD_CODE,
                P_I_V_DPD_MOVEMENT,
                P_I_V_POL_TERMINAL,
                P_I_V_CARGO_DECL,
                P_I_V_CREW_LST_DECL,
                P_I_V_PASSNGR_LIST,
                P_I_V_CUSTOM_ADD1,
                P_I_V_CUSTOM_ADD2,
                P_I_V_CUSTOM_ADD3,
                P_I_V_CUSTOM_ADD4,
                P_I_V_COLOR_FLAG,
                P_I_V_GROSS_CARGO_WEIGHT_BL_LEVEL,
                P_I_V_PACKAGE_BL_LEVEL,
                NULL,
                NULL,
                P_I_V_DEP_MANIF_NO,
                P_I_V_DEP_MANIFEST_DATE,
                P_I_V_SUBMITTER_TYPE,
                P_I_V_SUBMITTER_CODE,
                P_I_V_AUTHORIZ_REP_CODE,
                P_I_V_SHIPPING_LINE_BOND_NO_R,
                P_I_V_MODE_OF_TRANSPORT,
                P_I_V_SHIP_TYPE,
                P_I_V_CONVEYANCE_REFERENCE_NO,
                P_I_V_CARGO_DESCRIPTION,
                P_I_V_TOL_NO_OF_TRANS_EQU_MANIF,
                P_I_V_BRIEF_CARGO_DES,
                P_I_V_EXPECTED_DATE,
                P_I_V_TIME_OF_DEPT,
                P_I_V_TOTAL_NO_OF_TRAN_S_CONT_REPO_ON_ARI_DEP,
                P_I_V_MESSAGE_TYPE,
                P_I_V_VESSEL_TYPE_MOVEMENT,
                P_I_V_AUTHORIZED_SEA_CARRIER_CODE,
                P_I_V_PORT_OF_REGISTRY,
                P_I_V_REGISTRY_DATE,
                P_I_V_VOYAGE_DETAILS,
                P_I_V_SHIP_ITINERARY_SEQUENCE,
                P_I_V_SHIP_ITINERARY,
                P_I_V_PORT_OF_CALL_NAME,
                P_I_V_ARRIVAL_DEPARTURE_DETAILS,
                P_I_V_TOTAL_NO_OF_TRANSPORT_EQUIPMENT_REPORTED_ON_ARRIVAL_DEPARTURE
);
*/

INSERT INTO VASAPPS.IGM_NODIFY_PARTY (
                    PK_BL_NO,
                    CUSTOMER_NAME,
                    CUSTOMER_CODE,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE,
                    ZIP
) VALUES (

                     P_I_V_BL_NO,                 -- P_I_V_BL,
                    P_I_V_NOTIFYPARTY_NAME,
                    P_I_V_NOTIFYPARTY_CODE,
                    P_I_V_NOTIFYPARTY_ADD1,
                    P_I_V_NOTIFYPARTY_ADD2,
                    P_I_V_NOTIFYPARTY_ADD3,
                    P_I_V_NOTIFYPARTY_ADD4,
                   P_I_V_NOTIFYPARTY_CITY,
                   P_I_V_NOTIFYPARTY_STATE,
                    P_I_V_NOTIFYPARTY_COUNTRYCODE,
                   P_I_V_NOTIFYPARTY_ZIP

);

    -- INSERT INTO CHANDRA VALUES('to     '||P_I_V_BL_NO); COMMIT;

INSERT INTO VASAPPS.IGM_CONSIGNER_TABLE (
                    PK_BL_NO,
                    CONSIGNER_CODE,
                    CONSIGNER_NAME,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE,
                    ZIP
) VALUES (

                     P_I_V_BL_NO,               -- P_I_V_BL,
                    P_I_V_CONSIGNER_CODE,
                    P_I_V_CONSIGNER_NAME,
                    P_I_V_CONSIGNER_ADD1,
                    P_I_V_CONSIGNER_ADD2,
                    P_I_V_CONSIGNER_ADD3,
                    P_I_V_CONSIGNER_ADD4,
                    P_I_V_CONSIGNER_CITY,
                    P_I_V_CONSIGNER_STATE,
                    P_I_V_CONSIGNER_COUNTRYCODE,
                    P_I_V_CONSIGNER_ZIP

);

  --   INSERT INTO CHANDRA VALUES('three     '||P_I_V_BL_NO); COMMIT;

COMMIT;

      END RCL_IGM_SAVE_DATA;


 PROCEDURE RCL_IGM_BL_SAVE_GET_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR,
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
                             P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                             P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                             P_I_V_POL_TERMINAL VARCHAR2 DEFAULT NULL,
                             P_I_V_DIRECTION    VARCHAR2 DEFAULT NULL,
                             P_O_V_ERROR        OUT VARCHAR2)
  IS
    BL_COUNT NUMBER(20);
    SERVISE_COUNT NUMBER(20);
    TEMP_POD_COUNT_PRIVIOUS NUMBER(2);
    TEMP_POD_COUNT_NEXT NUMBER(4);
    TEMP_LAST_PORT_1  VARCHAR2(20);     --LAST_PORT_1
    TEMP_LAST_PORT_TER_1  VARCHAR2(20);
    TEMP_LAST_PORT_2  VARCHAR2(20);     --LAST_PORT_2
    TEMP_LAST_PORT_TER_2  VARCHAR2(20);
    TEMP_LAST_PORT_3  VARCHAR2(20);     --LAST_PORT_3
    TEMP_LAST_PORT_TER_3 VARCHAR2(20);
    TEMP_NEXT_PORT_1  VARCHAR2(20);     -- FOR 1ST NEXT PORT  NEXT_PORT_4
    TEMP_NEXT_PORT_2  VARCHAR2(20);     -- FOR 2ND NEXT PORT  NEXT_PORT_5
    TEMP_NEXT_PORT_3  VARCHAR2(20);     -- FOR 3RD NEXT PORT  NEXT_PORT_6
    TEMP_SERVICE VARCHAR2(20);
    TEMP_POD_TERMINAL VARCHAR2(20);
    V_SQL   VARCHAR2(6000);
    V_SQL_CNDTN  VARCHAR2(4000);
  BEGIN

       P_O_V_ERROR:='000000';

         OPEN P_O_REFIGMTABFIND FOR
          SELECT
		  BL_NO_PK as BL_NO,
		             SERVICE,
                     VESSEL,
                     VOYAGE,

                     POL_TERMINAL,
                     DEL_VLS,
                     DEPOT_VLS,
                     POD,
                    '' POD_TERMINAL,

                    '' BL_DATE,
                    '' BL_VERSION,
                    '' SUBMIT_DATE_TIME,
                    CONSOLIDATED_INDICATOR ,
                    PREVIOUS_DECLARATION ,
                    CONSOLIDATOR_PAN ,
                    CIN_TYPE ,
                    MCIN ,
                    CSN_SUBMITTED_TYPE ,
                    CSN_SUBMITTED_BY ,
                    CSN_REPORTING_TYPE ,
                    CSN_SITE_ID ,
                    CSN_NUMBER ,
                    CSN_DATE ,
                    PREVIOUS_MCIN ,
                    SPLIT_INDICATOR ,
                    NUMBER_OF_PACKAGES ,
                    TYPE_OF_PACKAGE ,
                    FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE ,
                    TYPE_OF_CARGO ,
                    SPLIT_INDICATOR_LIST ,
                    PORT_OF_ACCEPTANCE ,
                    PORT_OF_RECEIPT ,
                    UCR_TYPE ,
                    UCR_CODE ,
                    PORT_OF_ACCEPTANCE_NAME ,
                    PORT_OF_RECEIPT_NAME ,
                    PAN_OF_NOTIFIED_PARTY ,
                    UNIT_OF_WEIGHT ,
                    GROSS_VOLUME ,
                    UNIT_OF_VOLUME ,
                    CARGO_ITEM_SEQUENCE_NO ,
                    CARGO_ITEM_DESCRIPTION ,
                    CONTAINER_WEIGHT ,
                    NUMBER_OF_PACKAGES_HID ,
                    TYPE_OF_PACKAGES_HID ,
                    PORT_OF_CALL_SEQUENCE_NUMBER ,
                    PORT_OF_CALL_CODED ,
                    NEXT_PORT_OF_CALL_CODED ,
                    MC_LOCATION_CUSTOMS ,
                    UNO_CODE ,
                    IMDG_CODE ,
                    '' nhava_sheva_eta,
                    '' final_place_delivery,
                    '' packages,
                    '' cfs_name,
                    '' mbl_no,
                    '' hbl_no,
                    '' from_item_no,
                    '' to_item_no,
                    '' srl_no,
                    '' item_number,
                    '' cargo_movmnt
FROM IGM_BL_DETAILS_TWO IDP5

WHERE
                  ( P_I_V_SERVICE IS NULL
                        OR IDP5.SERVICE = P_I_V_SERVICE )
                 AND ( P_I_V_VESSEL IS NULL
                        OR IDP5.VESSEL = P_I_V_VESSEL )
                 AND ( P_I_V_VOYAGE IS NULL
                        OR IDP5.VOYAGE = P_I_V_VOYAGE )

                ;


      END RCL_IGM_BL_SAVE_GET_DATA;


      				 PROCEDURE RCL_IGM_SAVE_BL_DATA (
     					P_I_V_SERVICE                                                       VARCHAR2 DEFAULT NULL,
						P_I_V_VESSEL                                                        VARCHAR2 DEFAULT NULL,
						P_I_V_VOYAGE                                                        VARCHAR2 DEFAULT NULL,
						P_I_V_TERMINAL                                                      VARCHAR2 DEFAULT NULL,
						P_I_V_POL                                                           VARCHAR2 DEFAULT NULL,
						P_I_V_POL_TERMINAL                                                  VARCHAR2 DEFAULT NULL,
						P_I_V_POD                                                           VARCHAR2 DEFAULT NULL,
						P_I_V_BL_VEERSION                                                   VARCHAR2 DEFAULT NULL,
						P_I_V_CONSOLIDATOR_PAN                                              VARCHAR2 DEFAULT NULL,
						P_I_V_TYPE_OF_PACKAGE                                               VARCHAR2 DEFAULT NULL,
						P_I_V_CIN_TYPE                                                      VARCHAR2 DEFAULT NULL,
						P_I_V_TYPE_OF_CARGO                                                 VARCHAR2 DEFAULT NULL,
						P_I_V_ITEM_NUMBER                                                   VARCHAR2 DEFAULT NULL,
						P_I_V_MCIN                                                          VARCHAR2 DEFAULT NULL,
						P_I_V_CSN_SUBMITTED_TYPE                                            VARCHAR2 DEFAULT NULL,
						P_I_V_SPLIT_INDICATOR                                               VARCHAR2 DEFAULT NULL,
						P_I_V_CSN_SUBMITTED_BY                                              VARCHAR2 DEFAULT NULL,
						P_I_V_SUBMIT_DATE_TIME                                              VARCHAR2 DEFAULT NULL,
						P_I_V_BL_DATE                                                       VARCHAR2 DEFAULT NULL,
						P_I_V_CSN_REPORTING_TYPE                                            VARCHAR2 DEFAULT NULL,
						P_I_V_PORT_OF_ACCEPTANCE                                            VARCHAR2 DEFAULT NULL,
						P_I_V_CSN_SITE_ID                                                   VARCHAR2 DEFAULT NULL,
						P_I_V_DPD_MOVEMENT                                                  VARCHAR2 DEFAULT NULL,
						P_I_V_DPD_CODE                                                      VARCHAR2 DEFAULT NULL,
						P_I_V_BL_NO                                                         VARCHAR2 DEFAULT NULL,
						P_I_V_CARGO_NATURE                                                  VARCHAR2 DEFAULT NULL,
						P_I_V_CARGO_MOVMNT                                                  VARCHAR2 DEFAULT NULL,
						P_I_V_ITEM_TYPE                                                     VARCHAR2 DEFAULT NULL,
						P_I_V_CARGO_MOVMNT_TYPE                                             VARCHAR2 DEFAULT NULL,
						P_I_V_TRANSPORT_MODE                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CSN_NUMBER                                                    VARCHAR2 DEFAULT NULL,
						P_I_V_CONSOLIDATED_INDICATOR                                        VARCHAR2 DEFAULT NULL,
						P_I_V_CSN_DATE                                                      VARCHAR2 DEFAULT NULL,
						P_I_V_PREVIOUS_MCIN                                                 VARCHAR2 DEFAULT NULL,
						P_I_V_SPLIT_INDICATOR_LIST                                          VARCHAR2 DEFAULT NULL,
						P_I_V_PORT_OF_RECEIPT                                               VARCHAR2 DEFAULT NULL,
						P_I_V_NUMBER_OF_PACKAGES                                            VARCHAR2 DEFAULT NULL,
						P_I_V_PREVIOUS_DECLARATION                                          VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_ADD1                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_ADD2                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_ADD3                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_ADD4                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_COUNTRYCODE                                         VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_CODE                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_NAME                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_STATE                                               VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_ZIP                                                 VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNEE_CITY                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_ADD1                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_ADD2                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_ADD3                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_ADD4                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_COUNTRYCODE                                         VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_CODE                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_NAME                                                VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_STATE                                               VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_ZIP                                                 VARCHAR2 DEFAULT NULL,
						P_I_V_CONSIGNER_CITY                                                VARCHAR2 DEFAULT NULL,
						P_I_V_MARKSNUMBER_DESCRIPTION                                       VARCHAR2 DEFAULT NULL,
						P_I_V_MARKSNUMBER_MARKS                                             VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_ADD1                                              VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_ADD2                                              VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_ADD3                                              VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_ADD4                                              VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_CITY                                              VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_COUNTRYCODE                                       VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_CODE                                              VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_NAME                                              VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_STATE                                             VARCHAR2 DEFAULT NULL,
						P_I_V_NOTIFYPARTY_ZIP                                               VARCHAR2 DEFAULT NULL,
                        P_O_V_ERROR                                                          OUT VARCHAR2)     IS
                                                        V_SRL_NO NUMBER;
                                                        V_CNT NUMBER;
                                                        SEQ_NUMBER   NUMBER(10);



  BEGIN

 -- insert into chandra values('##################');  commit;
   delete from IGM_CONTAINER_DETAILS   where PK_BL_NO= P_I_V_BL_NO ;
   -- delete from igm_vessel_voyage_detailes   where PK_BL_NO= P_I_V_BL_NO ;
   delete from igm_consigner_table   where PK_BL_NO= P_I_V_BL_NO ;
    delete from IGM_MARKS_NUMBER_DESCRIPTION   where PK_BL_NO= P_I_V_BL_NO ;
    delete from IGM_NODIFY_PARTY   where PK_BL_NO= P_I_V_BL_NO ;
      delete from IGM_CONSIGNEE_TABLE  where PK_BL_NO= P_I_V_BL_NO ;
    delete from IGM_BL_DETAILS_TWO   where BL_NO_PK= P_I_V_BL_NO ;

   INSERT INTO VASAPPS.IGM_BL_DETAILS_TWO (
                    BL_NO_PK,
                    SERVICE  ,
                    VESSEL ,
                    VOYAGE   ,
                    POL_TERMINAL ,
                    POD  ,
                    CONSOLIDATED_INDICATOR,
                    PREVIOUS_DECLARATION,
                    CONSOLIDATOR_PAN,
                    CIN_TYPE,
                    MCIN,
                    CSN_SUBMITTED_TYPE,
                    CSN_SUBMITTED_BY,
                    CSN_REPORTING_TYPE,
                    CSN_SITE_ID,
                    CSN_NUMBER,
                    CSN_DATE,
                    PREVIOUS_MCIN,
                    SPLIT_INDICATOR,
                    NUMBER_OF_PACKAGES,
                    TYPE_OF_PACKAGE,
                    FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE,
                    TYPE_OF_CARGO,
                    SPLIT_INDICATOR_LIST,
                    PORT_OF_ACCEPTANCE,
                    PORT_OF_RECEIPT,
                    UCR_TYPE,
                    UCR_CODE,
                    PORT_OF_ACCEPTANCE_NAME,
                    PORT_OF_RECEIPT_NAME,
                    PAN_OF_NOTIFIED_PARTY,
                    UNIT_OF_WEIGHT,
                    GROSS_VOLUME,
                    UNIT_OF_VOLUME,
                    CARGO_ITEM_SEQUENCE_NO,
                    CARGO_ITEM_DESCRIPTION,
                    CONTAINER_WEIGHT,
                    NUMBER_OF_PACKAGES_HID,
                    TYPE_OF_PACKAGES_HID,
                    PORT_OF_CALL_SEQUENCE_NUMBER,
                    PORT_OF_CALL_CODED,
                    NEXT_PORT_OF_CALL_CODED,
                    MC_LOCATION_CUSTOMS,
                    UNO_CODE,
                    IMDG_CODE
        ) VALUES (
                    P_I_V_BL_NO,
                    P_I_V_SERVICE,
                    P_I_V_VESSEL,
                    P_I_V_VOYAGE,
                    P_I_V_POL_TERMINAL,
                    P_I_V_POD,
                    P_I_V_CONSOLIDATED_INDICATOR,
                    P_I_V_PREVIOUS_DECLARATION,
                    P_I_V_CONSOLIDATOR_PAN,
                    P_I_V_CIN_TYPE,
                    P_I_V_MCIN,
                    P_I_V_CSN_SUBMITTED_TYPE,
                    P_I_V_CSN_SUBMITTED_BY,
                    P_I_V_CSN_REPORTING_TYPE,
                    P_I_V_CSN_SITE_ID,
                    P_I_V_CSN_NUMBER,
                    P_I_V_CSN_DATE,
                    P_I_V_PREVIOUS_MCIN,
                    P_I_V_SPLIT_INDICATOR,
                    P_I_V_NUMBER_OF_PACKAGES,
                    P_I_V_TYPE_OF_PACKAGE,
                    '  ',
                    P_I_V_TYPE_OF_CARGO,
                    P_I_V_SPLIT_INDICATOR_LIST,
                    '  ',
                    P_I_V_PORT_OF_RECEIPT,
                    '  ',
                    '  ',
                    P_I_V_PORT_OF_ACCEPTANCE,
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  ',
                    '  '

							);

INSERT INTO VASAPPS.IGM_CONSIGNER_TABLE (

                    PK_BL_NO,
                    CONSIGNER_CODE,
                    CONSIGNER_NAME,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE ,
                   ZIP
) VALUES (

                    P_I_V_BL_NO,               -- P_I_V_BL,
                    P_I_V_CONSIGNER_CODE,
                    P_I_V_CONSIGNER_NAME,
                    P_I_V_CONSIGNER_ADD1,
                    P_I_V_CONSIGNER_ADD2,
                    P_I_V_CONSIGNER_ADD3,
                    P_I_V_CONSIGNER_ADD4,
                    P_I_V_CONSIGNER_CITY,
                    P_I_V_CONSIGNER_STATE,
                    P_I_V_CONSIGNER_COUNTRYCODE  ,
                  P_I_V_CONSIGNER_ZIP

);
INSERT INTO VASAPPS.IGM_NODIFY_PARTY (

                    PK_BL_NO,
                    CUSTOMER_NAME,
                    CUSTOMER_CODE,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE,
                    ZIP
) VALUES (

                    P_I_V_BL_NO,                 -- P_I_V_BL,
                    P_I_V_NOTIFYPARTY_NAME,
                    P_I_V_NOTIFYPARTY_CODE,
                    P_I_V_NOTIFYPARTY_ADD1,
                    P_I_V_NOTIFYPARTY_ADD2,
                    P_I_V_NOTIFYPARTY_ADD3,
                    P_I_V_NOTIFYPARTY_ADD4,
                    P_I_V_NOTIFYPARTY_CITY,
                    P_I_V_NOTIFYPARTY_STATE,
                    P_I_V_NOTIFYPARTY_COUNTRYCODE,
                    P_I_V_NOTIFYPARTY_ZIP

);

INSERT INTO VASAPPS.IGM_MARKS_NUMBER_DESCRIPTION (

						PK_BL_NO,
						MARKS_NUMBER,
						DESCRIPTION
) VALUES (

					P_I_V_BL_NO,
					P_I_V_MARKSNUMBER_MARKS,
					P_I_V_MARKSNUMBER_DESCRIPTION
);                                                               commit;


INSERT INTO VASAPPS.IGM_CONSIGNEE_TABLE (

                    PK_BL_NO,
                    CONSIGNEE_CODE,
                    CONSIGNEE_NAME,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE,
                    ZIP
) VALUES (

                    P_I_V_BL_NO,               -- P_I_V_BL,
                    P_I_V_CONSIGNEE_CODE,
                    P_I_V_CONSIGNEE_NAME,
                    P_I_V_CONSIGNEE_ADD1,
                    P_I_V_CONSIGNEE_ADD2,
                    P_I_V_CONSIGNEE_ADD3,
                    P_I_V_CONSIGNEE_ADD4,
                    P_I_V_CONSIGNEE_CITY,
                    P_I_V_CONSIGNEE_STATE,
                    P_I_V_CONSIGNEE_COUNTRYCODE,
                    P_I_V_CONSIGNEE_ZIP

);
           P_O_V_ERROR:='000000';
           commit;

      END RCL_IGM_SAVE_BL_DATA;

      -- shsil


      PROCEDURE RCL_IGM_SAVE_BL_DATA_SUSHIL(
                        P_I_V_BL	    VARCHAR2,

                        P_I_V_BL_DTLS   IN      CLOB
        )

        IS
      V_SQL varchar(1000);
        BEGIN

       -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);

     V_SQL:='DELETE FROM IGM_BL_DETAILS_TWO   WHERE BL_NO_PK in('|| P_I_V_BL||')';
      EXECUTE IMMEDIATE  V_SQL;
     COMMIT;
     DELETE FROM IGM_DATA_SET_JSON;

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_BL_DTLS); COMMIT;
    --Insert into chandra values(P_I_V_BL_DTLS);commit;
     UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';



     /*
     INSERT INTO IGM_BL_DETAILS
     SELECT BL_NO_PK,
                    SERVICE  ,
                    VESSEL ,
                    VOYAGE   ,
                    POL_TERMINAL ,
                    POD  ,
                    CONSOLIDATED_INDICATOR,
                    PREVIOUS_DECLARATION,
                    CONSOLIDATOR_PAN,
                    CIN_TYPE,
                    MCIN,
                    CSN_SUBMITTED_TYPE,
                    CSN_SUBMITTED_BY,
                    CSN_REPORTING_TYPE,
                    CSN_SITE_ID,
                    CSN_NUMBER,
                    CSN_DATE,
                    PREVIOUS_MCIN,
                    SPLIT_INDICATOR,
                    NUMBER_OF_PACKAGES,
                    TYPE_OF_PACKAGE,
                    FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE,
                    TYPE_OF_CARGO,
                    SPLIT_INDICATOR_LIST,
                    PORT_OF_ACCEPTANCE,
                    PORT_OF_RECEIPT,
                    UCR_TYPE,
                    UCR_CODE,
                    PORT_OF_ACCEPTANCE_NAME,
                    PORT_OF_RECEIPT_NAME,
                    PAN_OF_NOTIFIED_PARTY,
                    UNIT_OF_WEIGHT,
                    GROSS_VOLUME,
                    UNIT_OF_VOLUME,
                    CARGO_ITEM_SEQUENCE_NO,
                    CARGO_ITEM_DESCRIPTION,
                    CONTAINER_WEIGHT,
                    NUMBER_OF_PACKAGES_HID,
                    TYPE_OF_PACKAGES_HID,
                    PORT_OF_CALL_SEQUENCE_NUMBER,
                    PORT_OF_CALL_CODED,
                    NEXT_PORT_OF_CALL_CODED,
                    MC_LOCATION_CUSTOMS,
                    UNO_CODE,
                    IMDG_CODE ,
                    BL_DATE,
                    BL_VERSION ,
                    DEL_VLS ,
                    DEPOT_VLS


     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( BL_NO_PK PATH '$.bl'
                         , SERVICE PATH '$.service'
                         , VESSEL PATH '$.vessel'
                         , VOYAGE PATH '$.voyage'
                         , POL_TERMINAL PATH '$.podTerminal'
                         , POD PATH '$.pod'
                         , CONSOLIDATED_INDICATOR PATH '$.consolidated_indicator'
                         ,PREVIOUS_DECLARATION PATH '$.previous_declaration'

                         ,CONSOLIDATOR_PAN PATH '$.consolidator_pan'
                         ,CIN_TYPE PATH '$.cin_type'
                         ,MCIN PATH '$.mcin'
                          ,CSN_SUBMITTED_TYPE PATH '$.csn_submitted_type'
                          ,CSN_SUBMITTED_BY PATH '$.csn_submitted_by'
                          ,CSN_REPORTING_TYPE PATH '$.Csn_reporting_type'
                          ,CSN_SITE_ID PATH '$.csn_site_id'
                          ,CSN_NUMBER PATH '$.csn_number'
                         ,CSN_DATE PATH '$.csn_date'
                         ,PREVIOUS_MCIN PATH '$.previous_mcin'
                         ,SPLIT_INDICATOR PATH '$.split_indicator'
                         ,NUMBER_OF_PACKAGES PATH '$.number_of_packages'
                         ,TYPE_OF_PACKAGE PATH '$.type_of_package'
                         ,FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE PATH '$.first_port_of_entry_last_port_of_departure'
                         ,TYPE_OF_CARGO PATH '$.type_of_cargo'
                         ,SPLIT_INDICATOR_LIST PATH '$.split_indicator_list'
                         ,PORT_OF_ACCEPTANCE PATH '$.port_of_acceptance'
                         ,PORT_OF_RECEIPT PATH '$.port_of_receipt'

                         ,UCR_TYPE PATH '$.ucr_type'
                         ,UCR_CODE PATH '$.ucr_code'
                         ,PORT_OF_ACCEPTANCE_NAME PATH '$.port_of_acceptance_name'
                         ,PORT_OF_RECEIPT_NAME PATH '$.port_of_receipt_name'
                         ,PAN_OF_NOTIFIED_PARTY PATH '$.pan_of_notified_party'
                         ,UNIT_OF_WEIGHT PATH '$.unit_of_weight'
                         ,GROSS_VOLUME PATH '$.gross_volume'
                         ,UNIT_OF_VOLUME PATH '$.unit_of_volume'
                         ,CARGO_ITEM_SEQUENCE_NO PATH '$.cargo_item_sequence_no'
                         ,CARGO_ITEM_DESCRIPTION PATH '$.cargo_item_description'
                         ,CONTAINER_WEIGHT PATH '$.container_weight'
                         ,NUMBER_OF_PACKAGES_HID PATH '$.number_of_packages_hidden'
                         ,TYPE_OF_PACKAGES_HID PATH '$.type_of_packages_hidden'
                         ,PORT_OF_CALL_SEQUENCE_NUMBER PATH '$.port_of_call_sequence_number'
                         ,PORT_OF_CALL_CODED PATH '$.port_of_call_coded'
                         ,NEXT_PORT_OF_CALL_CODED PATH '$.next_port_of_call_coded'
                         ,MC_LOCATION_CUSTOMS PATH '$.mc_location_customs'
                         ,UNO_CODE PATH '$.uno_code'
                         ,IMDG_CODE PATH '$.imdg_code'
                         ,BL_DATE PATH '$.blDate'
                         ,BL_VERSION PATH '$.blVersion'
                         , DEL_VLS PATH '$.del'
                        ,DEPOT_VLS PATH '$.depot'

                        )
           );
      COMMIT;
       */
        END RCL_IGM_SAVE_BL_DATA_SUSHIL;


         PROCEDURE RCL_IGM_SAVE_CONSIGNEE_DATA(
                        P_I_V_BL	    VARCHAR2,
                        P_I_V_CONSIGNEE_DTLS   IN      CLOB
        )

        IS
      V_SQL varchar(1000);
        BEGIN

       -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);

     V_SQL:='DELETE FROM IGM_CONSIGNEE_TABLE   WHERE PK_BL_NO in('|| P_I_V_BL||')';
      EXECUTE IMMEDIATE  V_SQL;
     COMMIT;
     DELETE FROM IGM_DATA_SET_JSON;

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_CONSIGNEE_DTLS); COMMIT;

    -- Insert into chandra values('IGM_CONSIGNEE_TABLE    '||P_I_V_CONSIGNEE_DTLS);commit;
     UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';

   INSERT INTO IGM_CONSIGNEE_TABLE
     SELECT
                     PK_BL_NO,
                    CONSIGNEE_CODE,
                    CONSIGNEE_NAME,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE,
                    ZIP


     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( PK_BL_NO PATH '$.blNO'
                         , CONSIGNEE_CODE PATH '$.customerCode'
                         , CONSIGNEE_NAME PATH '$.customerName'
                         , ADDRESS_LINE_1 PATH '$.addressLine1'
                         , ADDRESS_LINE_2 PATH '$.addressLine2'
                         , ADDRESS_LINE_3 PATH '$.addressLine3'
                         , ADDRESS_LINE_4 PATH '$.addressLine4'
                         ,CITY PATH '$.city'
                         ,STATE PATH '$.state'
                         ,DN_COUNTRY_CODE PATH '$.countryCode'
                         ,ZIP PATH '$.zip'


                        )
           );
      COMMIT;

        END RCL_IGM_SAVE_CONSIGNEE_DATA;




         PROCEDURE RCL_IGM_SAVE_CONSIGNER_DATA(
                        P_I_V_BL	    VARCHAR2,
                        P_I_V_CONSIGNER_DTLS   IN      CLOB
        )

        IS
      V_SQL varchar(1000);
        BEGIN

       -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);

     V_SQL:='DELETE FROM IGM_CONSIGNER_TABLE   WHERE PK_BL_NO in('|| P_I_V_BL||')';
      EXECUTE IMMEDIATE  V_SQL;
     COMMIT;
     DELETE FROM IGM_DATA_SET_JSON;

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_CONSIGNER_DTLS); COMMIT;
     --Insert into chandra values(P_I_V_CONSIGNER_DTLS);commit;
     UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';

   INSERT INTO IGM_CONSIGNER_TABLE
     SELECT
                     PK_BL_NO,
                    CONSIGNER_CODE,
                    CONSIGNER_NAME,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE,
                    ZIP


     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( PK_BL_NO PATH '$.blNO'
                         , CONSIGNER_CODE PATH '$.customerCode'
                         , CONSIGNER_NAME PATH '$.customerName'
                         , ADDRESS_LINE_1 PATH '$.addressLine1'
                         , ADDRESS_LINE_2 PATH '$.addressLine2'
                         , ADDRESS_LINE_3 PATH '$.addressLine3'
                         , ADDRESS_LINE_4 PATH '$.addressLine4'
                         ,CITY PATH '$.city'
                         ,STATE PATH '$.state'
                         ,DN_COUNTRY_CODE PATH '$.countryCode'
                         ,ZIP PATH '$.zip'


                        )
           );
      COMMIT;

        END RCL_IGM_SAVE_CONSIGNER_DATA;

   PROCEDURE RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION_DATA(
                        P_I_V_BL	    VARCHAR2,
                        P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS   IN      CLOB
        )

        IS
      V_SQL varchar(1000);
        BEGIN

       -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);

     V_SQL:='DELETE FROM IGM_MARKS_NUMBER_DESCRIPTION   WHERE PK_BL_NO in('|| P_I_V_BL||')';
      EXECUTE IMMEDIATE  V_SQL;
     COMMIT;
     DELETE FROM IGM_DATA_SET_JSON;

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS); COMMIT;
     --Insert into chandra values(P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS);commit;
     UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';

   INSERT INTO IGM_MARKS_NUMBER_DESCRIPTION
     SELECT
                    PK_BL_NO,
                    MARKS_NUMBER ,
                    DESCRIPTION ,
                    REMARK

     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( PK_BL_NO PATH '$.blNO'
                         , MARKS_NUMBER PATH '$.MarksNumbers'
                         , DESCRIPTION PATH '$.Description'
                         , REMARK PATH '$.description'
                        )
           );
      COMMIT;

        END RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION_DATA;


        PROCEDURE RCL_IGM_SAVE_NODIFY_PARTY_DATA(
                        P_I_V_BL	    VARCHAR2,
                        P_I_V_NODIFY_PARTY_DTLS   IN      CLOB
        )

        IS
      V_SQL varchar(1000);
        BEGIN

       -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);

     V_SQL:='DELETE FROM IGM_NODIFY_PARTY   WHERE PK_BL_NO in('|| P_I_V_BL||')';
      EXECUTE IMMEDIATE  V_SQL;
     COMMIT;
     DELETE FROM IGM_DATA_SET_JSON;

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_NODIFY_PARTY_DTLS); COMMIT;
     --Insert into chandra values(P_I_V_NODIFY_PARTY_DTLS);commit;
     UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';

   INSERT INTO IGM_NODIFY_PARTY
     SELECT
                     PK_BL_NO,
                    CUSTOMER_CODE,
                    CUSTOMER_NAME,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE,
                    ZIP,
                    notify2


     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( PK_BL_NO PATH '$.blNo'
                         , CUSTOMER_CODE PATH '$.customerCode'
                         , CUSTOMER_NAME PATH '$.customerName'
                         , ADDRESS_LINE_1 PATH '$.addressLine1'
                         , ADDRESS_LINE_2 PATH '$.addressLine2'
                         , ADDRESS_LINE_3 PATH '$.addressLine3'
                         , ADDRESS_LINE_4 PATH '$.addressLine4'
                         ,CITY PATH '$.city'
                         ,STATE PATH '$.state'
                         ,DN_COUNTRY_CODE PATH '$.countryCode'
                         ,ZIP PATH '$.zip'
                         ,notify2 PATH '$.notify2'

                        )
           );
      COMMIT;

        END RCL_IGM_SAVE_NODIFY_PARTY_DATA;





	  END RCL_IGM_INFO_NEW;