create or replace PACKAGE BODY RCL_IGM_BL_INFO_EXPORT 
IS 



    PROCEDURE RCL_IGM_SAVE_CONTAINOR_NEW(      
        P_I_V_BL	    VARCHAR2,
        P_I_C_CONT_LST   IN      CLOB
        ) 
    
            IS
            V_SQL varchar(32000);  
            BEGIN
    
                V_SQL:='DELETE FROM IGM_CONTAINER_DETAILS_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
    
                EXECUTE IMMEDIATE  V_SQL; 
                COMMIT; 
                DELETE FROM IGM_DATA_SET_JSON;  
    
                INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_C_CONT_LST); COMMIT;                      
    
                --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';
    
                    INSERT INTO IGM_CONTAINER_DETAILS_TWO
                    SELECT PK_BL_NO
                    , DN_CONTAINER_NO
                    , CUSTOMER_SEAL_NO 
                    , EQUIPMENT_STATUS    
                    , QTY_PACKAGES
                    , METRIC_WEIGHT
                    , DN_EQ_SIZE
                    , DN_EQ_TYPE
                    , CONTAINER_BONDFLAG
                    , EQUIPMENT_LOAD_STATUS
                    , SOC_FLAG
                    , EQUIPMENT_SEAL_TYPE
                    , CONTAINER_AGENT
                    , CBM
                    , ISO_CODE
                    , UNOS 
                    , IMO
                    , REMARKS
                    , CLASSIFICATION            
                    , TYPE_DETAIL           
                    FROM  JSON_TABLE
                    (
                    (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                    COLUMNS ( PK_BL_NO PATH '$.blNo'
                    , DN_CONTAINER_NO PATH '$.containerNumber'
                    , CUSTOMER_SEAL_NO PATH '$.containerSealNumber'
                    , EQUIPMENT_STATUS PATH '$.status'
                    , QTY_PACKAGES PATH '$.totalNumberOfPackagesInContainer'
                    , METRIC_WEIGHT PATH '$.containerWeight'
                    , DN_EQ_SIZE PATH '$.containerSize' 
                    ,DN_EQ_TYPE PATH '$.containerType'
                    
                    ,CONTAINER_BONDFLAG PATH '$.containerBondFlag' 
                    ,EQUIPMENT_LOAD_STATUS PATH '$.equipmentLoadStatus'
                    ,SOC_FLAG PATH '$.soc_flag' 
                    ,EQUIPMENT_SEAL_TYPE PATH '$.equipment_seal_type' 
                    ,CONTAINER_AGENT PATH '$.containerAgentCode' 
                    ,CBM PATH '$.cbm' 
                    ,ISO_CODE PATH '$.isoCode' 
                    ,UNOS PATH '$.unos' 
                    ,IMO PATH '$.imo'
                    ,REMARKS PATH '$.remarks'
                    ,CLASSIFICATION PATH '$.classification'
                    ,TYPE_DETAIL PATH '$.typeDetail'
                    )
                    );
                    COMMIT;
    
    END RCL_IGM_SAVE_CONTAINOR_NEW;

    PROCEDURE RCL_IGM_SAVE_UNFETCHED_CONTAINOR_NEW(      
        P_I_V_BL	    VARCHAR2 
        ) 
            IS
            V_SQL varchar2(32000);  
            BEGIN
            
                V_SQL:='DELETE FROM IGM_CONTAINER_DETAILS_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
    
                EXECUTE IMMEDIATE  V_SQL; 
                COMMIT; 
                   
                V_SQL :=    'INSERT INTO IGM_CONTAINER_DETAILS_TWO
                    (PK_BL_NO
                    , DN_CONTAINER_NO
                    , CUSTOMER_SEAL_NO 
                    , EQUIPMENT_STATUS    
                    , QTY_PACKAGES
                    , METRIC_WEIGHT
                    , DN_EQ_SIZE
                    , DN_EQ_TYPE
                    , CONTAINER_BONDFLAG
                    , EQUIPMENT_LOAD_STATUS
                    , SOC_FLAG
                    , EQUIPMENT_SEAL_TYPE
                    , CONTAINER_AGENT
                    , CBM
                    , ISO_CODE
                    , UNOS 
                    , IMO
                    , REMARKS
                    , CLASSIFICATION            
                    , TYPE_DETAIL)           
                    SELECT 
                            RCLTBLS.DEX_BL_CONTAINERS.FK_BL_NO as PK_BL_NO,
                            DN_CONTAINER_NO, 
                            DEX_BL_CONTAINERS.CARRIER_SEAL  CUSTOMER_SEAL_NO,
                            DEX_BL_CONTAINERS.EQUIPMENT_STATUS  EQUIPMENT_STATUS,
                            RCLTBLS.DEX_BL_CONTAINERS.QTY_PACKAGES  QTY_PACKAGES,  
                            RCLTBLS.DEX_BL_CONTAINERS.METRIC_WEIGHT  METRIC_WEIGHT,
                            IDP055.EYEQSZ  DN_EQ_SIZE,
                            IDP055.EYEQTP  DN_EQ_TYPE,
                            RCLTBLS.DEX_BL_CONTAINERS.FLAG_DESC  CONTAINER_BONDFLAG,
                            DEX_BL_CONTAINERS.EQUIPMENT_STATUS  EQUIPMENT_LOAD_STATUS,
                            ''N'' SOC_FLAG,
                            ''''  EQUIPMENT_SEAL_TYPE ,
                            ''''  CONTAINER_AGENT,
                            ''''  CBM,
                            ''''  ISO_CODE,
                            IDP055.EYIUNO   UNOS,
                            '''' IMO,
                            ''''  REMARKS,
                            ''''  CLASSIFICATION,
                            ''''  TYPE_DETAIL
                            FROM RCLTBLS.DEX_BL_CONTAINERS INNER JOIN IDP055 ON IDP055.EYBLNO=RCLTBLS.DEX_BL_CONTAINERS.FK_BL_NO 
                            AND IDP055.EYEQNO=RCLTBLS.DEX_BL_CONTAINERS.DN_CONTAINER_NO 
                            WHERE 
                            RCLTBLS.DEX_BL_CONTAINERS.FK_BL_NO IN ('||P_I_V_BL||')';  
                    EXECUTE IMMEDIATE  V_SQL;   
                    COMMIT;
    
    END RCL_IGM_SAVE_UNFETCHED_CONTAINOR_NEW;
    
    PROCEDURE RCL_IGM_DELETE_CONTAINOR_NEW(    
        P_I_V_BL	    VARCHAR2,
        P_I_C_CONT_LST   IN      CLOB
        ) 
        
            IS
            V_SQL varchar(32672);
            BEGIN       
                V_SQL:='DELETE FROM IGM_CONTAINER_DETAILS_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')';                   
                EXECUTE IMMEDIATE  V_SQL; 
                COMMIT; 
    END RCL_IGM_DELETE_CONTAINOR_NEW;


    PROCEDURE RCL_IGM_GET_MASTER_CONTAINOR(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)    
        
            IS 
            l_v_sql VARCHAR2(32000);   
            BEGIN   
    
    
                l_v_sql:=   'SELECT 
                            ''origninal''   type,
                            RCLTBLS.DEX_BL_CONTAINERS.FK_BL_NO,
                            DN_CONTAINER_NO, 
                            ''''  SEALTYPE ,
                            DEX_BL_CONTAINERS.CARRIER_SEAL  SEAL_NO,
                            ''''  CONTAINER_AGENT,
                            DEX_BL_CONTAINERS.EQUIPMENT_STATUS  CONTAINER_STATUS,
                            '''' SOC_FLAG,
                            RCLTBLS.DEX_BL_CONTAINERS.FLAG_DESC  CONTAINER_BONDFLAG,
                            IDP055.EYEQTP   CONTAINERTYPE,
                            ''''  TYPE_DETAIL,
                            RCLTBLS.DEX_BL_CONTAINERS.QTY_PACKAGES  TOTAL_PACKAGES,         
                            RCLTBLS.DEX_BL_CONTAINERS.METRIC_WEIGHT  CONTAINER_WEIGHT,
                            ''''  CBM,
                            ''''  ISO_CODE,
                            IDP055.EYIUNO   UNOS,
                            '''' IMO,
                            ''''  CLASSIFICATION,
                            ''''  REMARKS,
                            '' '' EQUIPMENT_LOAD_STATUS,
                            IDP055.EYEQSZ  CONTAINERSIZE
                            FROM RCLTBLS.DEX_BL_CONTAINERS INNER JOIN IDP055 ON IDP055.EYBLNO=RCLTBLS.DEX_BL_CONTAINERS.FK_BL_NO 
                            AND IDP055.EYEQNO=RCLTBLS.DEX_BL_CONTAINERS.DN_CONTAINER_NO  
                            WHERE  RCLTBLS.DEX_BL_CONTAINERS.FK_BL_NO IN ('||P_I_V_BL||')';
    
             OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_MASTER_CONTAINOR;



    PROCEDURE RCL_IGM_GET_MASTER_CONSIGNER(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)     
        
            IS 
            l_v_sql VARCHAR2(32000);
            BEGIN   
    
    
                l_v_sql:=   'SELECT 
                            ''origninal''   type,
                            FK_BL_NO as FK_BL_NO,FK_CUSTOMER_CODE as CONSIGNER_CODE,CUSTOMER_NAME as 
                            CONSIGNER_NAME,ADDRESS_LINE_1,ADDRESS_LINE_2,ADDRESS_LINE_3,ADDRESS_LINE_4,CITY,STATE,ZIP,DN_COUNTRY_CODE,
                            (SELECT STATE_NAME FROM RCLTBLS.CAM_STATE WHERE PK_STATE_CODE IN 
                            (SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE FK_BL_NO = FK_BL_NO AND ROWNUM = 1) )    STATE_NAME
                            
                            FROM RCLTBLS.DEX_BL_CUSTOMERS 	
                            WHERE  CUSTOMER_TYPE =''S'' and FK_BL_NO IN ('||P_I_V_BL||')';
    
            OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_MASTER_CONSIGNER;


    PROCEDURE RCL_IGM_GET_SAVE_CONSIGNER(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)     
        
        IS 
        l_v_sql VARCHAR2(32000); 
        BEGIN   
    
    
            l_v_sql:=   '  SELECT 
                        ''origninal''   type,
                        PK_BL_NO as FK_BL_NO,
                        CONSIGNER_CODE ,
                        CONSIGNER_NAME,
                        ADDRESS_LINE_1,
                        ADDRESS_LINE_2,
                        ADDRESS_LINE_3,
                        ADDRESS_LINE_4,
                        CITY,
                        STATE,
                        DN_COUNTRY_CODE,
                        ZIP,
                        (SELECT STATE_NAME FROM RCLTBLS.CAM_STATE WHERE PK_STATE_CODE IN 
                        (SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE FK_BL_NO = PK_BL_NO AND ROWNUM = 1) AND ROWNUM = 1 )   STATE_NAME
                        from IGM_CONSIGNER_TABLE_TWO
                        WHERE   PK_BL_NO IN ('||P_I_V_BL||')';
    
            OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_SAVE_CONSIGNER;


    PROCEDURE RCL_IGM_GET_MASTER_CONSIGNEE(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)    
        
        IS 
        l_v_sql VARCHAR2(32000);
        BEGIN   
    
    
            l_v_sql:=   'SELECT 
                        ''origninal''   type, 
                        FK_BL_NO as FK_BL_NO,FK_CUSTOMER_CODE as CONSIGNEE_CODE,CUSTOMER_NAME as CONSIGNEE_NAME,
                        ADDRESS_LINE_1,ADDRESS_LINE_2,
                        ADDRESS_LINE_3,ADDRESS_LINE_4,CITY,STATE,ZIP,DN_COUNTRY_CODE,
                       (SELECT STATE_NAME FROM RCLTBLS.CAM_STATE WHERE PK_STATE_CODE IN 
                       (SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE FK_BL_NO = FK_BL_NO AND ROWNUM = 1) )    STATE_NAME,
                        
                         (SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C'') CONSIGNEES_NAME,
                        
                         (select DISCHARGE_PORT from IDP005 where SYBLNO IN (FK_BL_NO) AND rownum   =1) PORT_OF_DISCHARGE,
                         
                         (SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO) AND CYRCTP   = ''C''  AND rownum   =1) CONSIGNEE_CHECK_BOX, 
                         (SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND rownum   =1
                            ))                                                CONSIGNEE_FWR, 
                        
                         (CASE WHEN     
                            INSTR( UPPER( SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''BANK'')>0 OR 
                          INSTR( UPPER( SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER'')>0 or 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO THE ORDER OF'')>0 or
                                
                                INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER OF'')>0    THEN 
                          
                          (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1
                            )
                            )
                            
                        WHEN  INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030    WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''1''
                          AND rownum   =1 
                          ),1,70) ),''SAME AS CONSIGNEE'')>0  THEN 
                          
                          (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1
                            )
                          )  
                        WHEN INSTR( UPPER(    SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1
                            ) 
                          ),1,70) ),''FWR'')>0   THEN  
                           
                           (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1)
                          )
                           ELSE 
                            (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1
                            )
                            )
                           END)  CONSIGNEE_IEC,
                                                  
                    (CASE WHEN
                       INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C'' 
                          AND rownum   =1
                          ),1,70) ),''BANK'')>0 OR 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER'')>0 or 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO THE ORDER OF'')>0 or
                                
                                INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER OF'')>0    THEN 
                          
                          (SELECT FEDERAL_TAX_ID
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1
                            )
                            )
                            
                       WHEN (INSTR( UPPER( SUBSTR(( select DISCHARGE_PORT from IDP005 where SYBLNO IN (FK_BL_NO)  AND rownum   =1
                          ),1,2) ),''IN'')>0 AND
                          
                          INSTR( UPPER(SUBSTR(( select DN_COUNTRY_CODE  FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE =''C'' 
                          AND FK_BL_NO IN (FK_BL_NO) AND rownum   =1
                          ),1,2) ),''IN'')>0) THEN  
                          
                           (CASE WHEN     
                             INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO) AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''BANK'')>0 OR 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER'')>0 or 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO THE ORDER OF'')>0 or
                                
                                INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO) AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER OF'')>0    THEN 
                          
                          (SELECT FEDERAL_TAX_ID
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1
                            )
                            )
                            
                        WHEN  INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030    WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''1''
                          AND rownum   =1 
                          ),1,70) ),''SAME AS CONSIGNEE'')>0  THEN 
                          
                          (SELECT FEDERAL_TAX_ID
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1
                            )
                          )  
                        WHEN INSTR( UPPER(    SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1
                            ) 
                          ),1,70) ),''FWR'')>0   THEN  
                           
                           (SELECT FEDERAL_TAX_ID
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1
                            )
                          )
                           ELSE   
                            (SELECT FEDERAL_TAX_ID
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1
                            )
                            )
                           END)
            
                    ELSE 
                        (SELECT FEDERAL_TAX_ID
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1)
                            ) 
                        END)  CONSIGNEE_PAN
                        FROM RCLTBLS.DEX_BL_CUSTOMERS 
                        WHERE  CUSTOMER_TYPE =''C'' and FK_BL_NO IN ('||P_I_V_BL||')';
    
    
            OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_MASTER_CONSIGNEE;





    PROCEDURE RCL_IGM_GET_SAVE_CONSIGNEE(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)     
        
        IS 
        l_v_sql VARCHAR2(32000); 
        BEGIN   
    
    
                l_v_sql:=   '  SELECT 
                            ''origninal''   type,
                            PK_BL_NO as FK_BL_NO,
                            CONSIGNEE_CODE ,
                            CONSIGNEE_NAME,
                            ADDRESS_LINE_1,
                            ADDRESS_LINE_2,
                            ADDRESS_LINE_3,
                            ADDRESS_LINE_4,
                            CITY,
                            STATE,
                            DN_COUNTRY_CODE,
                            ZIP,
                           (SELECT STATE_NAME FROM RCLTBLS.CAM_STATE WHERE PK_STATE_CODE IN 
                           (SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE FK_BL_NO = FK_BL_NO AND ROWNUM = 1) )    STATE_NAME,
                        (SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C'' AND rownum   =1) CONSIGNEES_NAME,
                                                
                         (select DISCHARGE_PORT from IDP005 where SYBLNO IN (PK_BL_NO) AND rownum   =1) PORT_OF_DISCHARGE,
                         
                         (SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO) AND CYRCTP   = ''C''  AND rownum   =1) CONSIGNEE_CHECK_BOX, 
                         (SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (PK_BL_NO)
                            AND rownum   =1
                            ))                                                 CONSIGNEE_FWR, 
                        
         (CASE WHEN
                   INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C'' 
                      AND rownum   =1
                      ),1,70) ),''BANK'')>0 OR 
                      INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO ORDER'')>0 or 
                      INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO THE ORDER OF'')>0 or
                            
                            INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO ORDER OF'')>0    THEN 
                      
                      (SELECT SIC_CODE
                      FROM RCLTBLS.CAM_CUSTOMER
                      WHERE PK_CUSTOMER_CODE=
                        (SELECT CYCUST
                        FROM sealiner.IDP030
                        WHERE CYBLNO IN (PK_BL_NO)
                        AND CYRCTP   = ''C''
                        AND rownum   =1
                        )
                        )
                        
                   WHEN (INSTR( UPPER(    SUBSTR(( select DISCHARGE_PORT from IDP005 where SYBLNO IN (PK_BL_NO) AND rownum   =1
              ),1,2) ),''IN'')>0 AND
              
              INSTR( UPPER(    SUBSTR(( select DN_COUNTRY_CODE  FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE =''C'' AND   FK_BL_NO IN (PK_BL_NO) AND rownum   =1
              ),1,2) ),''IN'')>0) THEN  
              
               (CASE WHEN     
                 INSTR( UPPER( SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
              AND rownum   =1
              ),1,70) ),''BANK'')>0 OR 
              INSTR( UPPER( SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
              AND rownum   =1
              ),1,70) ),''TO ORDER'')>0 or 
              INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
              AND rownum   =1
              ),1,70) ),''TO THE ORDER OF'')>0 or
                    
                    INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
              AND rownum   =1
              ),1,70) ),''TO ORDER OF'')>0    THEN 
              
              (SELECT SIC_CODE
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (PK_BL_NO)
                AND CYRCTP   = ''C''
                AND rownum   =1
                )
                )
                
            WHEN  INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030    WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''1''
              AND rownum   =1 
              ),1,70) ),''SAME AS CONSIGNEE'')>0  THEN 
              
              (SELECT SIC_CODE
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (PK_BL_NO)
                AND CYRCTP   = ''C''
                AND rownum   =1
                )
              )  
            WHEN INSTR( UPPER(    SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (PK_BL_NO)
                AND CYRCTP   = ''1''
                AND rownum   =1
                ) 
              ),1,70) ),''FWR'')>0   THEN  
               
               (SELECT SIC_CODE
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (PK_BL_NO)
                AND CYRCTP   = ''C''
                AND rownum   =1
                )
              )
               ELSE 
                (SELECT SIC_CODE
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (PK_BL_NO)
                AND CYRCTP   = ''1''
                AND rownum   =1
                )
                )
               END)

        ELSE 
            (SELECT SIC_CODE
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (PK_BL_NO)
                AND CYRCTP   = ''1''
                AND rownum   =1)
                ) 
                   END)  CONSIGNEE_IEC,
                                      
        (CASE WHEN
                   INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C'' 
                      AND rownum   =1
                      ),1,70) ),''BANK'')>0 OR 
                      INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO ORDER'')>0 or 
                      INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO THE ORDER OF'')>0 or
                            
                            INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO ORDER OF'')>0    THEN 
                      
                      (SELECT FEDERAL_TAX_ID
                      FROM RCLTBLS.CAM_CUSTOMER
                      WHERE PK_CUSTOMER_CODE=
                        (SELECT CYCUST
                        FROM sealiner.IDP030
                        WHERE CYBLNO IN (PK_BL_NO)
                        AND CYRCTP   = ''C''
                        AND rownum   =1
                        )
                        )
                        
                   WHEN (INSTR( UPPER( SUBSTR(( select DISCHARGE_PORT from IDP005 where SYBLNO IN (PK_BL_NO)  AND rownum   =1
                      ),1,2) ),''IN'')>0 AND
                      
                      INSTR( UPPER(SUBSTR(( select DN_COUNTRY_CODE  FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE =''C'' AND FK_BL_NO IN (PK_BL_NO)
                       AND rownum   =1
                      ),1,2) ),''IN'')>0) THEN  
                      
                       (CASE WHEN     
                         INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO) AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''BANK'')>0 OR 
                      INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO ORDER'')>0 or 
                      INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO THE ORDER OF'')>0 or
                            
                            INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)  AND CYRCTP   = ''C''
                      AND rownum   =1
                      ),1,70) ),''TO ORDER OF'')>0    THEN 
                      
                      (SELECT FEDERAL_TAX_ID
                      FROM RCLTBLS.CAM_CUSTOMER
                      WHERE PK_CUSTOMER_CODE=
                        (SELECT CYCUST
                        FROM sealiner.IDP030
                        WHERE CYBLNO IN (PK_BL_NO)
                        AND CYRCTP   = ''C''
                        AND rownum   =1
                        )
                        )
                        
                    WHEN  INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030    WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''1''
                      AND rownum   =1 
                      ),1,70) ),''SAME AS CONSIGNEE'')>0  THEN 
                      
                      (SELECT FEDERAL_TAX_ID
                      FROM RCLTBLS.CAM_CUSTOMER
                      WHERE PK_CUSTOMER_CODE=
                        (SELECT CYCUST
                        FROM sealiner.IDP030
                        WHERE CYBLNO IN (PK_BL_NO)
                        AND CYRCTP   = ''C''
                        AND rownum   =1
                        )
                      )  
                    WHEN INSTR( UPPER(    SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                        (SELECT CYCUST
                        FROM sealiner.IDP030
                        WHERE CYBLNO IN (PK_BL_NO)
                        AND CYRCTP   = ''1''
                        AND rownum   =1
                        ) 
                      ),1,70) ),''FWR'')>0   THEN  
                       
                       (SELECT FEDERAL_TAX_ID
                      FROM RCLTBLS.CAM_CUSTOMER
                      WHERE PK_CUSTOMER_CODE=
                        (SELECT CYCUST
                        FROM sealiner.IDP030
                        WHERE CYBLNO IN (PK_BL_NO)
                        AND CYRCTP   = ''C''
                        AND rownum   =1
                        )
                      )
                       ELSE   
                        (SELECT FEDERAL_TAX_ID
                      FROM RCLTBLS.CAM_CUSTOMER
                      WHERE PK_CUSTOMER_CODE=
                        (SELECT CYCUST
                        FROM sealiner.IDP030
                        WHERE CYBLNO IN (PK_BL_NO)
                        AND CYRCTP   = ''1''
                        AND rownum   =1
                        )
                        )
                       END)
        
                ELSE 
                    (SELECT FEDERAL_TAX_ID
                      FROM RCLTBLS.CAM_CUSTOMER
                      WHERE PK_CUSTOMER_CODE=
                        (SELECT CYCUST
                        FROM sealiner.IDP030
                        WHERE CYBLNO IN (PK_BL_NO)
                        AND CYRCTP   = ''1''
                        AND rownum   =1)
                        ) 
                    END)  CONSIGNEE_PAN                           
                    from IGM_CONSIGNEE_TABLE_TWO
                            
                    WHERE   PK_BL_NO IN ('||P_I_V_BL||')';
    
            OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_SAVE_CONSIGNEE;


    PROCEDURE RCL_IGM_GET_SAVE_CONTAINOR(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)    
        
            IS 
            l_v_sql VARCHAR2(32000);
            BEGIN   
    
    
                l_v_sql:=   ' SELECT  PK_BL_NO FK_BL_NO,
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
                           EQUIPMENT_LOAD_STATUS,
                            DN_EQ_SIZE  CONTAINERSIZE
                            FROM  IGM_CONTAINER_DETAILS_TWO  
                            WHERE  PK_BL_NO IN ('||P_I_V_BL||')';
                
    
                OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    
    END RCL_IGM_GET_SAVE_CONTAINOR;



    PROCEDURE RCL_IGM_GET_MASTER_BL_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
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
                        V_SQL   VARCHAR2(32000); 
                        V_SQL_CNDTN  VARCHAR2(4000); 
                      BEGIN 

                            P_O_V_ERROR:='000000';    

                     OPEN P_O_REFIGMTABFIND FOR 
     SELECT SYBLNO                              BL_NO, 
                             IDP10.AYISDT                        BL_DATE,
                             SERVICE                             SERVICE, 
                             VESSEL                              VESSEL, 
                             VOYAGE                              VOYAGE, 
                             ACT_PORT_DIRECTION                  DIRECTION, 
                             FROM_TERMINAL                       POL_TERMINAL,
                             IDP10.AYDEST                        DEL_VLS,
                             TO_TERMINAL                         DEPOT_VLS,
                             DISCHARGE_PORT                      POD, 
                             TO_TERMINAL                         POD_TERMINAL,   
                              (SELECT MA_SEQ_NO 
                              FROM   RCLTBLS.DEX_BL_HEADER A 
                              WHERE  DN_POD = P_I_V_POL
                                     AND DISCHARGE_VESSEL = P_I_V_VESSEL 
                                     AND DISCHARGE_VOYAGE = P_I_V_VOYAGE 
                                     AND PK_BL_NO = IDP5.SYBLNO    
                                     AND ROWNUM < 2)             BL_VERSION,
                             --NEW BL SECTION FIELSD 
                            ''															    CONSOLIDATED_INDICATOR,
                            ''                                                              BL_TYPE, 
                            ''															    PREVIOUS_DECLARATION,	
                            ''															    CONSOLIDATOR_PAN,
                            ''															    CIN_TYPE,
                            ''															    MCIN,
                            ''															    CSN_SUBMITTED_TYPE,
                            ''															    CSN_SUBMITTED_BY,
                            ''                                                              CSN_REPORTING_TYPE,
                            ''															    CSN_SITE_ID,
                            ''															    CSN_NUMBER,
                            ''                                                              CSN_DATE,
                            ''                                                              PREVIOUS_MCIN,
                            ''                                                              SPLIT_INDICATOR,
                            ''                                                              ITEM_NUMBER, 
                            --janmejaya(26-05-2021) mapping given by (by guru)
                            ''															    ENBLOCK_MOVEMENT,--keep it blank
                            ''															    CARRIER_NO,--its carrier agent code (its use when we update Mode of Transport alongwith transporter)   it should come from carrier agent master for ICD Shipments (by guru)	
                            ''															    AGENCY_TYPE,--keep it blank (its in icl bl cargo page) 
                            ''															    INVOICE_VALUE_FC,--NEED TO KEEP BLANK (its OPTIONAL) 
                            ''  															INVOICE_VALUE_INR,--need to check (its in icl bl cargo page)  
                            'INR'															CURRENCY,--INR
                            ''															    CSN_SUBMITTED_BY,--keep it blank (its in icl bl cargo page)
                            ''                                                              MODEOF_TP_FEE,--keep it blank (its in icl bl cargo page)
                            ''															    REMARK,--keep it blank (its in icl bl cargo page)
                            ''															    SUB_LINE_NUMBER,--keep it blank (its in icl bl cargo page)
                            (select DN_POD     from RCLTBLS.DEX_BL_HEADER where PK_BL_NO= SYBLNO --) -- POR   
                            AND ROWNUM < 2)                                                 PORT_OF_DESTINATION,--Place of delivery (respective code from master)
                            (SELECT A.PARTNER_VALUE 
                            FROM   EDI_TRANSLATION_DETAIL A 
                            WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                            FROM   EDI_TRANSLATION_HEADER ETH 
                            WHERE  ETH.STANDARD = 'EDIFACT' 
                            AND ETH.CODE_SET = 'IGMPORT') 
                            AND A.SEALINER_VALUE = (select DN_POL   from RCLTBLS.DEX_BL_HEADER where PK_BL_NO= SYBLNO) -- POR   
                            AND ROWNUM < 2 )                                                PORT_OF_LOADING,--From DCS
                           (SELECT port_name FROM rcltbls.cam_port
                                 WHERE pk_port_code IN (select discharge_port from IDP005 where
                                 SYBLNO = p_i_v_bl AND ROWNUM=1))                 port_OF_call_name ,         
     
                            (select DN_PLD       from RCLTBLS.DEX_BL_HEADER where PK_BL_NO= SYBLNO --) -- POR   
                            AND ROWNUM < 2)                                                 PORT_OF_DESCHARGED_CFS,--We Will update 
                            ''                                                              MULTIPAL_PAKAGES,--keep it blank (its in icl bl cargo page) 
                            (select MET_MT from RCLTBLS.DEX_BL_HEADER 
                            where PK_BL_NO=SYBLNO)											CBM,--From DCS
                            ''																HIGHT_VALUE,--keep it blank (its in icl bl cargo page) 				
                            (select      MET_WT        from  RCLTBLS.DEX_BL_HEADER where  
                            PK_BL_NO=SYBLNO AND ROWNUM < 2)																GROS_WEIGHT,--From DCS grs.wt.column 				                
                            ''																UNIT,--From DCS unit   				                
                            ''																VOLUME,--From DCS volume		               
             
                            ( CASE 
                            WHEN AYMPOD = AYDEST THEN 'LC' 
                            ELSE 'TI' 
                            END )                                                           CARGO_MOVMNT, 
            
            
                            (SELECT SUM (EYPCKG) 
                             FROM IDP055 WHERE 
                             EYBLNO=SYBLNO AND ROWNUM=1)                                    NUMBER_OF_PACKAGES, --NEED TO DISCUSS
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
                            AND  SEALINER_VALUE=P_I_V_POL AND ROWNUM=1)                     FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE,
                            ''                                                              TYPE_OF_CARGO,
                            ''                                                              SPLIT_INDICATOR_LIST,
                            (SELECT  DN_POL     FROM RCLTBLS.DEX_BL_HEADER 
                            WHERE PK_BL_NO=SYBLNO AND ROWNUM=1)                                          PORT_OF_ACCEPTANCE,
                           (SELECT A.PARTNER_VALUE 
                              FROM   EDI_TRANSLATION_DETAIL A 
                              WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                                                   FROM   EDI_TRANSLATION_HEADER ETH 
                                                   WHERE  ETH.STANDARD = 'EDIFACT' 
                                                          AND ETH.CODE_SET = 'IGMPORT') 
                                     AND A.SEALINER_VALUE = (select DN_PLR from RCLTBLS.DEX_BL_HEADER where PK_BL_NO= SYBLNO) -- POR   
                                     AND ROWNUM < 2)                                        PORT_OF_RECEIPT,
                            ''                                                              UCR_TYPE,
                            ''                                                              UCR_CODE,
            
                          --  ''                                                              EQUIPMENT_SEAL_TYPE,
            
                            ''                                                              PORT_OF_ACCEPTANCE_NAME,
                            (SELECT TQTRNM DEPOT_NAME 
                            FROM ITP130 WHERE TQTERM=TO_TERMINAL-- DEL FROM BL 
                            AND    TQRCST='A' AND ROWNUM =1)                                                PORT_OF_RECEIPT_NAME,
                            (SELECT FEDERAL_TAX_ID PAN FROM SEALINER.ITP010
                            WHERE CURCST='A'
                            AND CUCUST IN (SELECT FK_CUSTOMER_CODE
                            FROM RCLTBLS.DEX_BL_CUSTOMERS
                            WHERE CUSTOMER_TYPE IN('N','1','2','3')
                            AND FK_BL_NO =SYBLNO AND ROWNUM=1)AND ROWNUM=1 )                     PAN_OF_NOTIFIED_PARTY,
                            (SELECT STMTWT FROM SEALINER.ITP0TD WHERE SGTRAD='*' AND ROWNUM=1)               UNIT_OF_WEIGHT,
                            (select MET_WT from  RCLTBLS.DEX_BL_HEADER 
                            where PK_BL_NO=SYBLNO AND ROWNUM = 1)                                                                GROSS_VOLUME,
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
                            VVPCAL=P_I_V_POL AND
                            VVVESS=P_I_V_VESSEL AND
                            VVVOYN=P_I_V_VOYAGE AND
                            --VVPCSQ=PORTSEQUENCE' AND
                            VVVERS=99 AND
                            
                            OMMISSION_FLAG IS NULL AND
                            VVFORL IS NOT NULL
                            AND ROWNUM =1)
                            AND OMMISSION_FLAG IS NULL
                            AND VVFORL IS NOT NULL
                            AND (VVPCAL ,VVTRM1) NOT IN
                            (SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
                            VVSRVC=ACT_SERVICE_CODE AND
                            VVPCAL=P_I_V_POL AND
                            VVVESS=P_I_V_VESSEL AND
                            VVVOYN=P_I_V_VOYAGE AND
                            VVTRM1=TO_TERMINAL AND
                            --VVPCSQ=PORTSEQUENCE' AND
                            VVVERS=99 AND
                            OMMISSION_FLAG IS NULL AND
                            VVFORL IS NOT NULL
                            AND ROWNUM = 1)
                            ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),'0000')
                            DESC,VVPCSQ DESC)WHERE ROWNUM=1)                            PORT_OF_CALL_CODED,
                             (
                               SELECT
                                   vvpcal
                               FROM
                                   (
                                       SELECT
                                           vvpcal
                                       FROM
                                           sealiner.itp063
                                       WHERE
                                           vvvess = p_i_v_vessel
                                           AND vvvers = 99
                                           AND ( vvardt
                                                 || nvl(lpad(vvartm, 4, 0), '0000') ) >= (
                                               SELECT
                                                   vvardt
                                                   || nvl(lpad(vvartm, 4, 0), '0000') AS etadate
                                               FROM
                                                   sealiner.itp063
                                               WHERE
                                                   vvsrvc = act_service_code
                                                   AND vvpcal = p_i_v_pol
                                                   AND vvvess = p_i_v_vessel
                                                   AND vvvoyn = p_i_v_voyage
                                                   AND
--VVPCSQ=PORTSEQUENCE' AND
                                                    vvvers = 99
                                                   AND ommission_flag IS NULL
                                                   AND vvforl IS NOT NULL
                                           )
                                           AND ommission_flag IS NULL
                                           AND vvforl IS NOT NULL
                                           AND ( vvpcal,
                                                 vvtrm1 ) NOT IN (
                                               SELECT
                                                   vvpcal,
                                                   vvtrm1
                                               FROM
                                                   sealiner.itp063
                                               WHERE
                                                   vvsrvc = act_service_code
                                                   AND vvpcal = p_i_v_pol
                                                   AND vvvess = p_i_v_vessel
                                                   AND vvvoyn = p_i_v_voyage
                                                   AND vvtrm1 = to_terminal
                                                   AND
--VVPCSQ =PORTSEQUENCE' AND
                                                    vvvers = 99
                                                   AND ommission_flag IS NULL
                                                   AND vvforl IS NOT NULL
                                           )
                                       ORDER BY
                                           vvardt
                                           || nvl(lpad(vvartm, 4, 0), '0000'),
                                           vvpcsq
                                   )
                               WHERE
                                   ROWNUM = 1
                           )                                                    next_port_of_call_coded,
                            
                   (
                               SELECT
                                   vvpcal
                               FROM
                                   (
                                       SELECT
                                           vvpcal
                                       FROM
                                           sealiner.itp063
                                       WHERE
                                           vvvess = p_i_v_vessel
                                           AND vvvers = 99
                                           AND ( vvardt
                                                 || nvl(lpad(vvartm, 4, 0), '0000') ) >= (
                                               SELECT
                                                   vvardt
                                                   || nvl(lpad(vvartm, 4, 0), '0000') AS etadate
                                               FROM
                                                   sealiner.itp063
                                               WHERE
                                                   vvsrvc = act_service_code
                                                   AND vvpcal = p_i_v_pol
                                                   AND vvvess = p_i_v_vessel
                                                   AND vvvoyn = p_i_v_voyage
                                                   AND
--VVPCSQ=PORTSEQUENCE' AND
                                                    vvvers = 99
                                                   AND ommission_flag IS NULL
                                                   AND vvforl IS NOT NULL
                                           )
                                           AND ommission_flag IS NULL
                                           AND vvforl IS NOT NULL
                                           AND ( vvpcal,
                                                 vvtrm1 ) NOT IN (
                                               SELECT
                                                   vvpcal,
                                                   vvtrm1
                                               FROM
                                                   sealiner.itp063
                                               WHERE
                                                   vvsrvc = act_service_code
                                                   AND vvpcal = p_i_v_pol
                                                   AND vvvess = p_i_v_vessel
                                                   AND vvvoyn = p_i_v_voyage
                                                   AND vvtrm1 = to_terminal
                                                   AND
--VVPCSQ =PORTSEQUENCE' AND
                                                    vvvers = 99
                                                   AND ommission_flag IS NULL
                                                   AND vvforl IS NOT NULL
                                           )
                                       ORDER BY
                                           vvardt
                                           || nvl(lpad(vvartm, 4, 0), '0000'),
                                           vvpcsq
                                   )
                               WHERE
                                   ROWNUM = 1
                           ) next_port_of_call_coded,
                           
                           (SELECT port_name FROM   rcltbls.cam_port
                             WHERE pk_port_code IN 
                                (SELECT   vvpcal FROM
                                    (  SELECT  vvpcal  FROM sealiner.itp063
                                        WHERE vvvess = p_i_v_vessel
                                            AND vvvers = 99
                                            AND ( vvardt || nvl(lpad(vvartm, 4, 0), '0000') ) >= (
                                            SELECT vvardt || nvl(lpad(vvartm, 4, 0), '0000') AS etadate
                                                FROM  sealiner.itp063
                                                WHERE
                                                     vvsrvc = act_service_code
                                                   AND vvpcal = p_i_v_pol
                                                   AND vvvess = p_i_v_vessel
                                                   AND vvvoyn = p_i_v_voyage
                                                    AND vvvers = 99
                                                    AND ommission_flag IS NULL
                                                    AND vvforl IS NOT NULL
                                                    AND ROWNUM =1)
                                                    AND ommission_flag IS NULL
                                                    AND vvforl IS NOT NULL
                                                    AND ( vvpcal, vvtrm1 ) NOT IN (
                                                SELECT   vvpcal,   vvtrm1
                                                FROM  sealiner.itp063
                                                WHERE
                                                     vvsrvc = act_service_code
                                                   AND vvpcal = p_i_v_pol
                                                   AND vvvess = p_i_v_vessel
                                                   AND vvvoyn = p_i_v_voyage
                                                   AND vvtrm1 = to_terminal
                                                    AND   vvvers = 99
                                                    AND ommission_flag IS NULL
                                                    AND vvforl IS NOT NULL
                                                    AND ROWNUM = 1)
                                        ORDER BY vvardt || nvl(lpad(vvartm, 4, 0), '0000'),
                                            vvpcsq  )  WHERE ROWNUM = 1  ))                     next_port_of_call_name,
                            
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
                             (select      TOTAL_PKG      from  RCLTBLS.DEX_BL_HEADER where    PK_BL_NO=SYBLNO AND ROWNUM < 2)                                  PACKAGES, 
                             ''                                  CFS_NAME, 
                             ''                                  MBL_NO, 
                             (SELECT FK_HOUSE_BL_NO  FROM RCLTBLS.DEX_BL_HEADER 
                             WHERE FK_BOOKING_NO IN (
                             SELECT FK_BOOKING_NO FROM    
                             RCLTBLS.DEX_BL_HEADER WHERE PK_BL_NO= SYBLNO
                             ) AND FK_HOUSE_BL_NO IS NOT NULL AND ROWNUM=1)                                  HBL_NO, 
                             ''                                  FROM_ITEM_NO, 
                             ''                                  TO_ITEM_NO,
                             ''                                  SRL_NO,
                             ''                                  LINE_CODE,
                             ''                                  DPD_CODE                         , 
                             ''                                  DPD_MOVEMENT,
                             ''                                  DUTY_INR,
                             ''                                  ROAD_CARR_CODE,
                             ''                                  ROAD_TP_BOND_NO,
                             'FCL'                               NE_CARGO_MOVMNT,
                             ''                                  CONSIGNEE_NAME,
                             
                            (select FLAG_DG from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO = syblno  AND ROWNUM=1) flag_dg,
                             (select DN_COMMODITY_CODE from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO = syblno  AND ROWNUM=1) COMMODITY_CODE,
                            (select DN_PACKAGE_KIND from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO = syblno AND ROWNUM=1) PACKAGE_KIND,
                            (select COMMODITY_SEQ from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO = syblno  AND ROWNUM=1) COMMODITY_SEQ ,
                            
                            (select METRIC_MSMT from  RCLTBLS.dex_bl_containers
                             WHERE FK_BL_NO = syblno AND rownum =1)  cargo_msmt,
--                         (CASE WHEN (SELECT DN_LOAD_PORT FROM rcltbls.dex_bl_routing  WHERE 
--                                FK_BL_NO  IN (SYBLNO) AND FK_VESSEL IN (ACT_VESSEL_CODE) and FK_VOYAGE IN (ACT_VOYAGE_NUMBER)
--                                AND FK_ACT_VESSEL_CODE  IN (ACT_VESSEL_CODE)
--                               AND FK_ACT_VOYAGE_NUMBER IN (ACT_VOYAGE_NUMBER)) = DISCHARGE_PORT 
--                               THEN 
--                                'LOADED' 
--                               ELSE 
--                                'NOT'
--                             END)  FLAG_LOADED,
--                                     
--                        (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT FLAG_DISCHARGE FROM IGM_BL_DETAILS WHERE  BL_NO_PK IN (SYBLNO)
--                                 AND rownum   =1),1,70) ),'LOADED')>0
--                                  THEN 
--                                     'BL LOADED'
--                                 ELSE
--                                     'BL NOT LOADED'
--                               END) BL_LOADED_STATUS, 
--                        (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT BL_DISCHARGE_STATUS FROM IGM_BL_DETAILS WHERE  BL_NO_PK IN (SYBLNO)
--                                AND rownum   =1),1,70) ),'BL NOT LOADED')>0
--                                 THEN 
--                                    'ROB BOX'
--                                 ELSE
--                                     'N'
--                             END) FLAG_ROB_DISCHARGE,
--                        
--                        (CASE WHEN (SELECT DN_DISCHARGE_PORT FROM rcltbls.dex_bl_routing  WHERE 
--                                     FK_BL_NO  IN (SYBLNO)
--                                AND FK_ACT_VESSEL_CODE  IN (ACT_VESSEL_CODE)
--                               AND FK_ACT_VOYAGE_NUMBER IN (ACT_VOYAGE_NUMBER)) = DISCHARGE_PORT THEN 'DISCHARGED' 
--                                  ELSE 'NOT'
--                                     END)  FLAG_DISCHARGE,
                             ''  FLAG_DISCHARGE,        
                            (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT FLAG_DISCHARGE FROM IGM_BL_DETAILS_TWO WHERE  BL_NO_PK IN (SYBLNO)
                                 AND rownum   =1),1,70) ),'DISCHARGED')>0
                                      THEN 
                                          'BL DISCHARGED'
                                          ELSE
                                          'BL NOT DISCHARGED'
                                          END) BL_DISCHARGE_STATUS,
                            (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT BL_DISCHARGE_STATUS FROM IGM_BL_DETAILS_TWO WHERE  BL_NO_PK IN (SYBLNO)
                                AND rownum   =1),1,70) ),'BL NOT DISCHARGED')>0
                                      THEN 
                                          'ROB BOX'
                                          ELSE
                                          'N'
                                          END) FLAG_ROB,
                                      
                             (SELECT  count(FK_HOUSE_BL_NO) FROM  RCLTBLS.DEX_BL_HEADER h WHERE PK_BL_NO IN (
                        (SELECT PK_BL_NO  FROM  RCLTBLS.DEX_BL_HEADER WHERE FK_PART_OF_BL_NO = SYBLNO  AND ROWNUM=1)) AND ROWNUM=1) HBLCOUNT,
                            
                        (SELECT POINT_NAME  from rcltbls.cam_point WHERE PK_POINT_CODE IN
                            (select  DN_PLR   from RCLTBLS.DEX_BL_HEADER where PK_BL_NO = SYBLNO  AND ROWNUM=1 )  AND ROWNUM=1)  RECIEPT_NAME,
                           (select  DN_PLR   from RCLTBLS.DEX_BL_HEADER where PK_BL_NO = SYBLNO  AND ROWNUM=1)    DN_PLR,
                           
                        (SELECT POINT_NAME  from rcltbls.cam_point WHERE PK_POINT_CODE IN 
                            (select  DN_PLD   from RCLTBLS.DEX_BL_HEADER where PK_BL_NO = SYBLNO  AND ROWNUM=1)  AND ROWNUM=1)  ACCEPTANCE_NAME,
                           (select  DN_PLD   from RCLTBLS.DEX_BL_HEADER where PK_BL_NO = SYBLNO  AND ROWNUM=1)    DN_PLD,
                           
                         (SELECT GST_STATE_CODE FROM SEALINER.ITP185 WHERE STCODE IN (
                          SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE  FK_BL_NO =SYBLNO  AND customer_type = 'S' AND ROWNUM = 1)AND ROWNUM = 1)   GST_STATE_CODE,
                           
                                   ( SELECT  NVL(STOWAGE_POSITION,'NULL') FROM   VASAPPS.TOS_DL_BOOKED_DISCHARGE WHERE
                            FK_DISCHARGE_LIST_ID IN  
                            (SELECT PK_DISCHARGE_LIST_ID  FROM   VASAPPS.TOS_DL_discharge_LIST WHERE 
                                 FK_VESSEL = p_i_v_vessel AND FK_VOYAGE = p_i_v_voyage AND ROWNUM = 1
                                ) and FK_BOOKING_NO IN ( select FK_BOOKING_NO  from   RCLTBLS.BL_BOOKING_MAPPING WHERE
                                FK_BL_NO = SYBLNO AND ROWNUM =1) and dn_equipment_no  = (select  DN_CONTAINER_NO  FROM RCLTBLS.DEX_BL_CONTAINERS  
                                   WHERE FK_BL_NO  IN (SYBLNO) AND rownum = 1)      FETCH FIRST 1 ROW ONLY) stowage_position,
                           (select sailing_date from rcltbls.dex_bl_routing where FK_BL_NO = SYBLNO and ROWNUM = 1)   MASTER_BL_DATE        
    
                       FROM   IDP005 IDP5 
                                INNER JOIN IDP010 IDP10 
                                ON IDP5.SYBLNO = IDP10.AYBLNO 
                                WHERE 
                                    IDP5.ACT_VESSEL_CODE = p_i_v_vessel
                                AND IDP5.ACT_VOYAGE_NUMBER = p_i_v_voyage
                                AND IDP5.SYBLNO =p_i_v_bl;

    END RCL_IGM_GET_MASTER_BL_DATA;


    PROCEDURE RCL_IGM_GET_MASTER_BL_DATA_NEW(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
        P_I_V_POD          VARCHAR2, 
        P_I_V_BL           VARCHAR2 DEFAULT NULL, 
        P_I_V_BL_COUNT      VARCHAR2 DEFAULT NULL,
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
            V_SQL   VARCHAR2(32000); 
            V_SQL_CNDTN  VARCHAR2(4000); 
            BL_NO_INPUT  VARCHAR2(4000); 
            
            BEGIN   


                    P_O_V_ERROR:='000000';
                    
                      BL_NO_INPUT := P_I_V_BL;
                insert into sidtest values(BL_NO_INPUT);
                commit;
         
                FOR i IN (
                        SELECT
                            TRIM(regexp_substr(P_I_V_BL, '[^,]+', 1, level)) l
                        FROM
                            dual
                        CONNECT BY
                            level <= regexp_count(P_I_V_BL, ',') + 1
                    ) LOOP 
                    
                    
                    delete from dex_bl_header_temp where mbl_no = SUBSTR(i.l,2,LENGTH(i.l)-2);commit;
                    INSERT INTO dex_bl_header_temp (mbl_no, ma_seq_no,  dn_pod,  dn_pol, met_mt, met_wt, dn_plr, dn_pld,  point_name,  hbl_count) 
                                    SELECT 
                                        pk_bl_no,
                                        ma_seq_no,
                                        dn_pod,
                                        dn_pol,
                                        met_mt,   
                                        met_wt,   
                                        dn_plr,
                                        dn_pld,
                                        (SELECT point_name FROM rcltbls.cam_point  WHERE
                                         pk_point_code = dn_pld  AND ROWNUM = 1) point_name,
                                    fk_house_bl_no
                                    FROM
                                        rcltbls.dex_bl_header
                                    WHERE
                                        pk_bl_no = SUBSTR(i.l,2,LENGTH(i.l)-2) ;
                     commit;
                     
                     delete from dex_bl_commodity_temp where MBL_NO = SUBSTR(i.l,2,LENGTH(i.l)-2);commit;
                     INSERT INTO  dex_bl_commodity_temp
                                (MBL_NO,FLAG_DG,DN_COMMODITY_CODE,DN_PACKAGE_KIND,COMMODITY_SEQ) 
                                select 
                                FK_BL_NO                                                                                        MBL_NO,
                                FLAG_DG                                                                                         flag_dg,
                                DN_COMMODITY_CODE                                                                               COMMODITY_CODE,
                                DN_PACKAGE_KIND                                                                                 PACKAGE_KIND,
                                COMMODITY_SEQ                                                                                   COMMODITY_SEQ
                                from RCLTBLS.dex_bl_commodity
                                WHERE FK_BL_NO IN (SUBSTR(i.l,2,LENGTH(i.l)-2)) and rownum = 1;
                       commit;    
                       
                       delete from dex_bl_containers_temp where mbl_no = SUBSTR(i.l,2,LENGTH(i.l)-2);commit;
                       INSERT INTO dex_bl_containers_temp (  mbl_no,     net_msmt_metric,  metric_msmt )
                                SELECT
                                    fk_bl_no,
                                    net_msmt_metric,
                                    metric_msmt 
                                FROM rcltbls.dex_bl_containers
                                WHERE fk_bl_no = SUBSTR(i.l,2,LENGTH(i.l)-2)
                                    AND ROWNUM = 1;
                        commit;
                    END LOOP;
                    
    
                    V_SQL:=     'select 
                                SYBLNO                                                                                          BL_NO, 
                                IDP10.AYISDT                                                                                    BL_DATE,
                                ACT_SERVICE_CODE                                                                                SERVICE, 
                                ACT_VESSEL_CODE                                                                                 VESSEL, 
                                ACT_VOYAGE_NUMBER                                                                               VOYAGE, 
                                ACT_PORT_DIRECTION                                                                              DIRECTION, 
                                FROM_TERMINAL                                                                                   POL_TERMINAL,
                                IDP10.AYDEST                                                                                    DEL_VLS,
                                TO_TERMINAL                                                                                     DEPOT_VLS,
                                DISCHARGE_PORT                                                                                  POD, 
                                ( CASE 
                                WHEN AYMPOD = AYDEST THEN ''LC''
                                ELSE ''TI'' 
                                END )                                                                                           CARGO_MOVMNT,  
                                (CASE WHEN IDP5.DISCHARGE_PORT = (DISCHARGE_PORT) 
									THEN ''DISCHARGED'' 
									ELSE ''NOT''
                                    END)                                                                                        FLAG_DISCHARGE,
                                (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT FLAG_DISCHARGE 
                                FROM IGM_BL_DETAILS_TWO WHERE  BL_NO_PK IN (SYBLNO)
									AND rownum   =1),1,70) ),''DISCHARGED'')>0
                                    THEN 
                                          ''BL DISCHARGED''
                                          ELSE
                                          ''BL NOT DISCHARGED''
                                          END)                                                                                  BL_DISCHARGE_STATUS,
                                (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT BL_DISCHARGE_STATUS
                                FROM IGM_BL_DETAILS_TWO WHERE  BL_NO_PK IN (SYBLNO)
                                AND rownum   =1),1,70) ),''BL NOT DISCHARGED'')>0
                                      THEN 
                                          ''ROB BOX''
                                          ELSE
                                          ''N''
                                          END)                                                                                  FLAG_ROB, 
                                NET_MSMT_METRIC                                                                                 GROSS_VOLUME,
                                METRIC_MSMT                                                                                     cargo_msmt,          
                                FINAL_PLACE_OF_DELIVERY_NAME                                                                    FINAL_PLACE_DELIVERY,     
                                ma_seq_no                                                                                       BL_VERSION,
                                dn_pod                                                                                          PORT_OF_DESTINATION,
                                dn_pol                                                                                          PORT_OF_LOADING,
                                met_mt                                                                                          CBM,
                                met_wt                                                                                          GROS_WEIGHT,
                                dn_plr                                                                                          dn_plr,
                                dn_plr                                                                                          RECIEPT_NAME,
                                dn_pld                                                                                          ACCEPTANCE_NAME,
                                dn_pld                                                                                          dn_pld,
                                point_name                                                                                      point_name,
                                hbl_count                                                                                       hbl_count,
                                ''S''															                                CONSOLIDATED_INDICATOR,  
                                ''SOC/COC''                                                                                     BL_TYPE,     
                                ''''															                                PREVIOUS_DECLARATION,	
                                ''''															                                CONSOLIDATOR_PAN,
                                ''''															                                CIN_TYPE,
                                ''''															                                MCIN,
                                ''''															                                CSN_SUBMITTED_TYPE,
                                ''''															                                CSN_SUBMITTED_BY,
                                ''''                                                                                            CSN_REPORTING_TYPE,
                                ''''															                                CSN_SITE_ID,
                                ''''															                                CSN_NUMBER,
                                ''''                                                                                            CSN_DATE,
                                ''''                                                                                            PREVIOUS_MCIN,
                                ''''                                                                                            SPLIT_INDICATOR,
                                ''''                                                                                            ITEM_NUMBER, 
                                ''''															                                ENBLOCK_MOVEMENT, 
                                ''''															                                CARRIER_NO,
                                ''''															                                AGENCY_TYPE, 
                                ''''															                                INVOICE_VALUE_FC,
                                ''''  															                                INVOICE_VALUE_INR,
                                ''INR''															                                CURRENCY,
                                ''P''                                                                                           MODEOF_TP_FEE,
                                ''''															                                REMARK,
                                ''''															                                SUB_LINE_NUMBER,
                                ''''                                                                                            MULTIPAL_PAKAGES,
                                ''''																                            HIGHT_VALUE, 		
                                ''''																                            UNIT, 		                
                                ''''																                            VOLUME, 
                                ''''                                                                                            TYPE_OF_CARGO,
                                ''''                                                                                            SPLIT_INDICATOR_LIST,
                                ''''                                                                                            UCR_TYPE,
                                ''''                                                                                            UCR_CODE,
                                ''''                                                                                            PORT_OF_ACCEPTANCE_NAME,
                                ''UOV''                                                                                         UNIT_OF_VOLUME,
                                ''CISN''                                                                                        CARGO_ITEM_SEQUENCE_NO,
                                ''NOPH''                                                                                        NUMBER_OF_PACKAGES_HID,
                                ''PORTOCSN''                                                                                    PORT_OF_CALL_SEQUENCE_NUMBER,
                                ''MCLC''                                                                                        MC_LOCATION_CUSTOMS,
                                ''''                                                                                            CONTAINER_WEIGHT,
                                ''''                                                                                            NHAVA_SHEVA_ETA, 
                                ''''                                                                                            PACKAGES, 
                                ''''                                                                                            CFS_NAME, 
                                ''''                                                                                            MBL_NO,
                                ''''                                                                                            FROM_ITEM_NO, 
                                ''''                                                                                            TO_ITEM_NO,
                                ''''                                                                                            SRL_NO,
                                ''''                                                                                            LINE_CODE,
                                ''''                                                                                            DPD_CODE, 
                                ''''                                                                                            DPD_MOVEMENT,
                                ''''                                                                                            DUTY_INR,
                                ''''                                                                                            ROAD_CARR_CODE,
                                ''''                                                                                            ROAD_TP_BOND_NO ,
                                ''FCL''                                                                                         NE_CARGO_MOVMNT,
                                FLAG_DG                                                                                         FLAG_DG,
                                DN_COMMODITY_CODE                                                                               COMMODITY_CODE,
                                DN_PACKAGE_KIND                                                                                 PACKAGE_KIND,
                                COMMODITY_SEQ                                                                                   COMMODITY_SEQ,
                                (SELECT STMTWT FROM SEALINER.ITP0TD
                                WHERE SGTRAD=''*'' AND ROWNUM=1)                                                                UNIT_OF_WEIGHT,  
                                (SELECT  FK_IMO_CLASS  FROM  RCLTBLS.DEX_BL_SPECIAL_CARGO_DTL SP
                                , RCLTBLS.DEX_BL_COMMODITY CM WHERE CM.DN_SPECIAL_HNDL=''D1''
                                AND CM.FK_BL_NO=SP.FK_BL_NO 
                                AND CM.COMMODITY_SEQ=SP.COMMODITY_SEQ
                                AND ROWNUM=1 AND CM.FK_BL_NO= SYBLNO AND ROWNUM=1)                                              IMDG_CODE,
                                      
                                (SELECT  max(FK_UNNO)  FROM  RCLTBLS.DEX_BL_SPECIAL_CARGO_DTL SP 
                                , RCLTBLS.DEX_BL_COMMODITY CM WHERE CM.DN_SPECIAL_HNDL=''D1''
                                AND CM.FK_BL_NO=SP.FK_BL_NO 
                                AND CM.COMMODITY_SEQ=SP.COMMODITY_SEQ)                                                          UNO_CODE,
                               (select PORT_NAME  from rcltbls.cam_port where PK_PORT_CODE IN 
                               (DISCHARGE_PORT))                                                                                port_of_call_name,
                               (SELECT SUM (EYPCKG) 
                                FROM IDP055 WHERE 
                                 EYBLNO=SYBLNO AND ROWNUM=1)                                                                    NUMBER_OF_PACKAGES, 
                                (SELECT TQTRNM DEPOT_NAME 
                                FROM ITP130 WHERE TQTERM=TO_TERMINAL 
                                AND    TQRCST=''A'' AND ROWNUM=1)                                                               PORT_OF_RECEIPT_NAME,
                                (SELECT BYRMKS FROM IDP050 WHERE BYBLNO=SYBLNO AND ROWNUM=1)                                    CARGO_ITEM_DESCRIPTION,
                                                              
                                (SELECT GST_STATE_CODE FROM SEALINER.ITP185 WHERE STCODE IN (
                                SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE  
                                CUSTOMER_TYPE =''C'' and FK_BL_NO = SYBLNO AND ROWNUM = 1)AND ROWNUM =1)                        GST_STATE_CODE,
                                (SELECT FEDERAL_TAX_ID PAN FROM SEALINER.ITP010
                                WHERE CURCST=''A''
                                AND CUCUST IN (SELECT FK_CUSTOMER_CODE
                                FROM RCLTBLS.DEX_BL_CUSTOMERS
                                WHERE CUSTOMER_TYPE IN(''N'',''1'',''2'',''3'')
                                AND FK_BL_NO = SYBLNO AND ROWNUM=1)AND ROWNUM=1 )                                               PAN_OF_NOTIFIED_PARTY,
                                (SELECT A.PARTNER_VALUE 
                                FROM   EDI_TRANSLATION_DETAIL A 
                                WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                                FROM   EDI_TRANSLATION_HEADER ETH 
                                WHERE  ETH.STANDARD = ''EDIFACT'' 
                                AND ETH.CODE_SET = ''IGMPORT'') 
                                AND A.SEALINER_VALUE =  DN_PLD)                                                                 PORT_OF_DESCHARGED_CFS,
                                (SELECT max(PARTNER_VALUE)
                                FROM EDI_TRANSLATION_DETAIL 
                                WHERE ETH_UID IN (
                                SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                                WHERE CODE_SET=''IGMTML''
                                ) AND SEALINER_VALUE=(SELECT max(TO_TERMINAL) 
                                FROM IDP005 
                                WHERE 1=1
                                AND ACT_VESSEL_CODE=''ALR''
                                AND ACT_VOYAGE_NUMBER=''2253W'' 
                                AND DISCHARGE_PORT=''INMUN'' 
                                AND ACT_SERVICE_CODE=''RWX'') )                                                                 POD_TERMINAL,
                                (SELECT PARTNER_VALUE 
                                FROM SEALINER.EDI_TRANSLATION_HEADER HDR,
                                SEALINER.EDI_TRANSLATION_DETAIL DTL,
                                RCLTBLS.DEX_BL_COMMODITY BL 
                                WHERE  HDR.CODE_SET=''IGMPKGKIND'' 
                                AND HDR.ETH_UID=DTL.ETH_UID
                                AND DN_PACKAGE_KIND=SEALINER_VALUE 
                                AND BL.FK_BL_NO= SYBLNO  AND ROWNUM=1)                                                          TYPE_OF_PACKAGE,
                                (SELECT PARTNER_VALUE FROM SEALINER.EDI_TRANSLATION_HEADER HDR
                                ,SEALINER.EDI_TRANSLATION_DETAIL DTL  
                                WHERE  HDR.CODE_SET=''IGMPORT'' AND HDR.ETH_UID=DTL.ETH_UID
                                AND  SEALINER_VALUE=''INMUN'' AND ROWNUM=1)                                                     FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE,
                                (SELECT PARTNER_VALUE 
                                FROM SEALINER.EDI_TRANSLATION_HEADER HDR,
                                SEALINER.EDI_TRANSLATION_DETAIL DTL,
                                RCLTBLS.DEX_BL_COMMODITY BL 
                                WHERE  HDR.CODE_SET=''IGMPKGKIND'' 
                                AND HDR.ETH_UID=DTL.ETH_UID
                                AND DN_PACKAGE_KIND=SEALINER_VALUE 
                                AND BL.FK_BL_NO= SYBLNO AND ROWNUM=1)                                                           TYPE_OF_PACKAGES_HID,
                                (SELECT VVPCAL FROM (
                                SELECT VVPCAL FROM SEALINER.ITP063
                                WHERE VVVESS = ('''||P_I_V_VESSEL||''') AND VVVERS= 99
                                AND(VVARDT||NVL(LPAD(VVARTM,4,0),''0000''))<=(
                                SELECT VVARDT||NVL(LPAD(VVARTM,4,0),''0000'') 
                                AS ETADATE FROM SEALINER.ITP063 WHERE
                                VVSRVC=ACT_SERVICE_CODE AND
                                VVPCAL=DISCHARGE_PORT AND
                                VVVESS=('''||P_I_V_VESSEL||''') AND
                                VVVOYN= ('''||P_I_V_VOYAGE||''') AND
                                
                                VVVERS=99 AND
                                OMMISSION_FLAG IS NULL AND
                                VVFORL IS NOT NULL )
                                AND OMMISSION_FLAG IS NULL
                                AND VVFORL IS NOT NULL
                                AND (VVPCAL ,VVTRM1) NOT IN
                                (SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
                                VVSRVC=ACT_SERVICE_CODE AND
                                VVPCAL=DISCHARGE_PORT AND
                                VVVESS=('''||P_I_V_VESSEL||''') AND
                                VVVOYN= ('''||P_I_V_VOYAGE||''') AND
                                VVTRM1=TO_TERMINAL AND
                                
                                VVVERS=99 AND
                                OMMISSION_FLAG IS NULL AND
                                VVFORL IS NOT NULL)
                                ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),''0000'')
                                DESC,VVPCSQ DESC)WHERE ROWNUM=1)                                                                PORT_OF_CALL_CODED,
                                (SELECT VVPCAL FROM (
                                SELECT VVPCAL FROM SEALINER.ITP063 WHERE 
                                VVVESS = ('''||P_I_V_VESSEL||''') AND VVVERS= 99 
                                AND(VVARDT||NVL(LPAD(VVARTM,4,0),''0000''))>=(
                                SELECT VVARDT||NVL(LPAD(VVARTM,4,0),''0000'')
                                AS ETADATE FROM SEALINER.ITP063 WHERE
                                VVSRVC=ACT_SERVICE_CODE AND
                                VVPCAL=DISCHARGE_PORT AND
                                VVVESS=('''||P_I_V_VESSEL||''') AND
                                VVVOYN= ('''||P_I_V_VOYAGE||''') AND
                                
                                VVVERS=99 AND
                                OMMISSION_FLAG IS NULL AND
                                VVFORL IS NOT NULL )
                                AND OMMISSION_FLAG IS NULL
                                AND VVFORL IS NOT NULL
                                AND (VVPCAL ,VVTRM1) NOT IN
                                ( SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
                                VVSRVC =ACT_SERVICE_CODE AND
                                VVPCAL =DISCHARGE_PORT AND
                                VVVESS =('''||P_I_V_VESSEL||''') AND
                                VVVOYN = ('''||P_I_V_VOYAGE||''') AND
                                VVTRM1=TO_TERMINAL AND
                                
                                VVVERS =99 AND
                                OMMISSION_FLAG IS NULL AND
                                VVFORL IS NOT NULL)
                                ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),''0000''),VVPCSQ)
                                WHERE ROWNUM=1)                                                                                 NEXT_PORT_OF_CALL_CODED,
                                
                                (SELECT
                                 port_name FROM rcltbls.cam_port
                                 WHERE pk_port_code IN(
                                 SELECT VVPCAL FROM (
                                SELECT VVPCAL FROM SEALINER.ITP063 WHERE 
                                VVVESS = ('''||P_I_V_VESSEL||''') AND VVVERS= 99 
                                AND(VVARDT||NVL(LPAD(VVARTM,4,0),''0000''))>=(
                                SELECT VVARDT||NVL(LPAD(VVARTM,4,0),''0000'')
                                AS ETADATE FROM SEALINER.ITP063 WHERE
                                VVSRVC=ACT_SERVICE_CODE AND
                                VVPCAL=DISCHARGE_PORT AND
                                VVVESS=('''||P_I_V_VESSEL||''') AND
                                VVVOYN= ('''||P_I_V_VOYAGE||''') AND
                                
                                VVVERS=99 AND
                                OMMISSION_FLAG IS NULL AND
                                VVFORL IS NOT NULL )
                                AND OMMISSION_FLAG IS NULL
                                AND VVFORL IS NOT NULL
                                AND (VVPCAL ,VVTRM1) NOT IN
                                ( SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
                                VVSRVC =ACT_SERVICE_CODE AND
                                VVPCAL =DISCHARGE_PORT AND
                                VVVESS =('''||P_I_V_VESSEL||''') AND
                                VVVOYN = ('''||P_I_V_VOYAGE||''') AND
                                VVTRM1=TO_TERMINAL AND
                                
                                VVVERS =99 AND
                                OMMISSION_FLAG IS NULL AND
                                VVFORL IS NOT NULL)
                                ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),''0000''),VVPCSQ)
                                WHERE ROWNUM=1) )                                                                               next_port_of_call_name,                                
                                (SELECT  NVL(STOWAGE_POSITION,''NULL'') FROM   VASAPPS.TOS_DL_BOOKED_DISCHARGE WHERE
                                 FK_DISCHARGE_LIST_ID IN  
                                 (SELECT PK_DISCHARGE_LIST_ID  FROM   VASAPPS.TOS_DL_discharge_LIST WHERE 
                                 FK_VESSEL = ('''||P_I_V_VESSEL||''') AND FK_VOYAGE = ('''||P_I_V_VOYAGE||''') AND ROWNUM = 1
                                 ) and FK_BOOKING_NO IN ( select FK_BOOKING_NO  from   RCLTBLS.BL_BOOKING_MAPPING WHERE
                                 FK_BL_NO =  SYBLNO) and dn_equipment_no  IN 
                                 (select  DN_CONTAINER_NO  FROM RCLTBLS.DEX_BL_CONTAINERS  
                                 WHERE FK_BL_NO  IN (SYBLNO) AND rownum = 1)      FETCH FIRST 1 ROW ONLY)                       stowage_position,
                                ''0''                                                                                           HBLCOUNT,
                                ''''                                                                                            HBL_NO,
                                ( SELECT  CUSTOMER_NAME as CONSIGNEE_NAME
                                FROM RCLTBLS.DEX_BL_CUSTOMERS  WHERE  CUSTOMER_TYPE =''C''
                                and FK_BL_NO IN (SYBLNO)     AND rownum = 1 )                                                   CONSIGNEE_NAME,
                                ( SELECT CUSTOMER_NAME as CONSIGNEE_NAME FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE  CUSTOMER_TYPE =''C''
                                    and FK_BL_NO IN (SYBLNO))   CONSIGNEE_NAME,
                    
                                (select sailing_date from rcltbls.dex_bl_routing where
                                 FK_BL_NO = SYBLNO  AND ROWNUM = 1)                                                             MASTER_BL_DATE
                                FROM   IDP005 IDP5 
                                INNER JOIN IDP010 IDP10  ON IDP5.SYBLNO = IDP10.AYBLNO
                                left OUTER JOIN dex_bl_commodity_temp dbct ON dbct.MBL_NO = IDP5.SYBLNO
                                left OUTER JOIN dex_bl_header_temp dbht ON dbht.MBL_NO = IDP5.SYBLNO   
                                left OUTER JOIN dex_bl_containers_temp dblct ON dblct.MBL_NO = IDP5.SYBLNO
                                WHERE
                                IDP5.ACT_VESSEL_CODE = ('''||P_I_V_VESSEL||''')
                                AND IDP5.ACT_VOYAGE_NUMBER = ('''||P_I_V_VOYAGE||''')
                                AND IDP5.SYBLNO in ('||P_I_V_BL||')'; 
    
    
                OPEN P_O_REFIGMTABFIND FOR V_SQL;	  
    
    END RCL_IGM_GET_MASTER_BL_DATA_NEW;

     PROCEDURE  RCL_IGM_SAVE_UNFETCHED_BL_DATA_SUSHIL ( 
                             P_I_V_BL           VARCHAR2 DEFAULT NULL,
                             P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,
                             P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL,
                             P_I_V_BL_JSON      IN      CLOB
                              ) 
         
        
            IS   
            V_SQL_DELETE varchar2(32000);
            V_SQL varchar2(32000);
            BL_NO_QRY  VARCHAR2(32000);
            V_SQL_BL varchar2(32000);
            V_SQL_CNDTN  VARCHAR2(4000); 
            BL_NO_INPUT  VARCHAR2(32000); 
            item_no_count NUMBER;
            BEGIN  
                
                FOR i IN (
                        SELECT
                            TRIM(regexp_substr(P_I_V_BL, '[^,]+', 1, level)) l
                        FROM
                            dual
                        CONNECT BY
                            level <= regexp_count(P_I_V_BL, ',') + 1
                    ) LOOP  
                    
                    delete from dex_bl_header_temp where mbl_no = SUBSTR(i.l,2,LENGTH(i.l)-2);commit;
                    INSERT INTO dex_bl_header_temp (mbl_no, ma_seq_no,  dn_pod,  dn_pol, met_mt, met_wt, dn_plr, dn_pld,  point_name,  hbl_count) 
                                    SELECT 
                                        pk_bl_no,
                                        ma_seq_no,
                                        dn_pod,
                                        dn_pol,
                                        met_mt,   
                                        met_wt,   
                                        dn_plr,
                                        dn_pld,
                                        (SELECT point_name FROM rcltbls.cam_point  WHERE
                                         pk_point_code = dn_pld  AND ROWNUM = 1) point_name,
                                    fk_house_bl_no
                                    FROM
                                        rcltbls.dex_bl_header
                                    WHERE
                                        pk_bl_no = SUBSTR(i.l,2,LENGTH(i.l)-2) ;
                     commit;
                     
                     delete from dex_bl_commodity_temp where MBL_NO = SUBSTR(i.l,2,LENGTH(i.l)-2);commit;
                     INSERT INTO  dex_bl_commodity_temp
                                (MBL_NO,FLAG_DG,DN_COMMODITY_CODE,DN_PACKAGE_KIND,COMMODITY_SEQ) 
                                select 
                                FK_BL_NO                                                                                        MBL_NO,
                                FLAG_DG                                                                                         flag_dg,
                                DN_COMMODITY_CODE                                                                               COMMODITY_CODE,
                                DN_PACKAGE_KIND                                                                                 PACKAGE_KIND,
                                COMMODITY_SEQ                                                                                   COMMODITY_SEQ
                                from RCLTBLS.dex_bl_commodity
                                WHERE FK_BL_NO IN (SUBSTR(i.l,2,LENGTH(i.l)-2)) and rownum = 1;
                       commit;    
                       
                       delete from dex_bl_containers_temp where mbl_no = SUBSTR(i.l,2,LENGTH(i.l)-2);commit;
                       INSERT INTO dex_bl_containers_temp (  mbl_no,     net_msmt_metric,  metric_msmt )
                                SELECT
                                    fk_bl_no,
                                    net_msmt_metric,
                                    metric_msmt 
                                FROM rcltbls.dex_bl_containers
                                WHERE fk_bl_no = SUBSTR(i.l,2,LENGTH(i.l)-2)
                                    AND ROWNUM = 1;
                        commit;
                  
                    END LOOP;
             
                V_SQL_DELETE:=   'delete from IGM_BL_DETAILS_TWO where BL_NO_PK IN ('||P_I_V_BL||')';
                EXECUTE IMMEDIATE  V_SQL_DELETE; commit;   
                
                DELETE FROM IGM_DATA_SET_JSON;
                INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_BL_JSON); COMMIT;
                
                V_SQL_DELETE:=   'delete from BL_ITEAM_NO_TEMP where BL_NO IN ('||P_I_V_BL||')';
                EXECUTE IMMEDIATE  V_SQL_DELETE; commit; 
                
                INSERT INTO BL_ITEAM_NO_TEMP
                SELECT 
                BL_NO,
                ITEM_NUM             
                FROM  JSON_TABLE
                ((SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( BL_NO PATH '$.bl'
                         ,ITEM_NUM PATH '$.itemNumber'));COMMIT;
             
                V_SQL:=    'INSERT INTO IGM_BL_DETAILS_TWO
                            (BL_NO_PK,
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
                            DEPOT_VLS,
                            ENBLOCK_MOVEMENT,
                            CARRIER_NO,	
                            AGENCY_TYPE,
                            INVOICE_VALUE_FC,
                            INVOICE_VALUE_INR,
                            CURRENCY,
                            MODEOF_TP_FEE,
                            REMARK,
                            SUB_LINE_NUMBER,
                            PORT_OF_DESTINATION,
                            PORT_OF_LOADING,
                            PORT_OF_DESCHARGED_CFS,
                            MULTIPAL_PAKAGES,
                            CBM,
                            HIGHT_VALUE,				
                            GROS_WEIGHT,				                
                            UNIT,				                
                            VOLUME,
                            ITEM_NUMBER,
                            CARGO_MOVMNT ,
                            DPD_CODE ,
                            DPD_MOVEMENT,
                            DUTY_INR,
                            ROAD_CARR_CODE,
                            ROAD_TP_BOND_NO,      
                            BL_TYPE,
                            NE_CARGO_MOVMNT,
                            FLAG_DISCHARGE,
                            BL_DISCHARGE_STATUS,
                            FLAG_ROB,
                            STOWAGE_POSITION,
                            FK_HOUSE_BL_NO,
                            GST_STATE_CODE,
                            DN_PLR,
                            DN_PLD,
                            ACCEPTANCE_NAME,
                            RECIEPT_NAME,
                            port_OF_call_name,
                            next_port_of_call_name,
                            MASTER_BL_DATE)
                            select 
                            SYBLNO                                                                                          BL_NO, 
                            ACT_SERVICE_CODE                                                                                SERVICE,
                            ACT_VESSEL_CODE                                                                                 VESSEL, 
                            ACT_VOYAGE_NUMBER                                                                               VOYAGE, 
                            FROM_TERMINAL                                                                                   POL_TERMINAL,
                            
                            DISCHARGE_PORT                                                                                  POD, 
                            ''S''															                                    CONSOLIDATED_INDICATOR,  
                            ''''															                                    PREVIOUS_DECLARATION,
                            ''''															                                	CONSOLIDATOR_PAN,
                            ''''															                                	CIN_TYPE,
                            ''''															                                	MCIN,
                            ''''															                                	CSN_SUBMITTED_TYPE,
                            ''''															                                	CSN_SUBMITTED_BY,
                            ''''                                                                                              CSN_REPORTING_TYPE,
                            ''''															                                CSN_SITE_ID,
                            ''''															                                CSN_NUMBER,
                            ''''                                                                                           CSN_DATE,
                            ''''                                                                                            PREVIOUS_MCIN,
                            ''''                                                                                            SPLIT_INDICATOR,
                            (SELECT SUM (EYPCKG) 
                            FROM IDP055 WHERE 
                            EYBLNO=SYBLNO AND ROWNUM=1)                                                                    NUMBER_OF_PACKAGES, 
                            (SELECT PARTNER_VALUE 
                            FROM SEALINER.EDI_TRANSLATION_HEADER HDR,
                            SEALINER.EDI_TRANSLATION_DETAIL DTL,
                            RCLTBLS.DEX_BL_COMMODITY BL 
                            WHERE  HDR.CODE_SET=''IGMPKGKIND'' 
                            AND HDR.ETH_UID=DTL.ETH_UID
                            AND DN_PACKAGE_KIND=SEALINER_VALUE 
                            AND BL.FK_BL_NO= SYBLNO  AND ROWNUM=1)                                                          TYPE_OF_PACKAGE,
                            (SELECT PARTNER_VALUE FROM SEALINER.EDI_TRANSLATION_HEADER HDR
                            ,SEALINER.EDI_TRANSLATION_DETAIL DTL  
                            WHERE  HDR.CODE_SET=''IGMPORT'' AND HDR.ETH_UID=DTL.ETH_UID
                            AND  SEALINER_VALUE=''INMUN'' AND ROWNUM=1)                                                     FIRST_PORT_OF_ENTRY_LAST_PORT_OF_DEPARTURE,
                            ''''                                                                                            TYPE_OF_CARGO,
                            ''''                                                                                            SPLIT_INDICATOR_LIST,
                            dn_pld 																						     PORT_OF_ACCEPTANCE,
                            dn_plr        																					 PORT_OF_RECEIPT,
                            ''''                                                                                               UCR_TYPE,
                            ''''                                                                                               UCR_CODE,
                            point_name                                                                                       PORT_OF_ACCEPTANCE_NAME,
                            (SELECT TQTRNM DEPOT_NAME 
                            FROM ITP130 WHERE TQTERM=TO_TERMINAL 
                            AND    TQRCST=''A'' AND ROWNUM=1)                                                                 PORT_OF_RECEIPT_NAME,
                            
                            (SELECT FEDERAL_TAX_ID PAN FROM SEALINER.ITP010
                            WHERE CURCST=''A''
                            AND CUCUST IN (SELECT FK_CUSTOMER_CODE
                            FROM RCLTBLS.DEX_BL_CUSTOMERS
                            WHERE CUSTOMER_TYPE IN(''N'',''1'',''2'',''3'')
                            AND FK_BL_NO = SYBLNO AND ROWNUM=1)AND ROWNUM=1 )                                               PAN_OF_NOTIFIED_PARTY,
                            
                            (SELECT STMTWT FROM SEALINER.ITP0TD
                            WHERE SGTRAD=''*'' AND ROWNUM=1)                                                                  UNIT_OF_WEIGHT, 
                            
                            NET_MSMT_METRIC                                                                                 GROSS_VOLUME,
                            ''UOV''                                                                                        	UNIT_OF_VOLUME,
                            ''CISN''                                                                                        	CARGO_ITEM_SEQUENCE_NO,
                            (SELECT BYRMKS FROM IDP050 WHERE BYBLNO=SYBLNO AND ROWNUM=1)                                    CARGO_ITEM_DESCRIPTION,
                            ''''                                                                                            	CONTAINER_WEIGHT,
                            ''NOPH''                                                                                        	NUMBER_OF_PACKAGES_HID,
                            (SELECT PARTNER_VALUE 
                            FROM SEALINER.EDI_TRANSLATION_HEADER HDR,
                            SEALINER.EDI_TRANSLATION_DETAIL DTL,
                            RCLTBLS.DEX_BL_COMMODITY BL 
                            WHERE  HDR.CODE_SET=''IGMPKGKIND'' 
                            AND HDR.ETH_UID=DTL.ETH_UID
                            AND DN_PACKAGE_KIND=SEALINER_VALUE 
                            AND BL.FK_BL_NO= SYBLNO AND ROWNUM=1)                                                           TYPE_OF_PACKAGES_HID, 
                            ''PORTOCSN''                                                                                      PORT_OF_CALL_SEQUENCE_NUMBER,
                            
                            (SELECT VVPCAL FROM (
                            SELECT VVPCAL FROM SEALINER.ITP063
                            WHERE VVVESS = ('''||P_I_V_VESSEL||''') AND VVVERS= 99
                            AND ROWNUM =1 
                            AND(VVARDT||NVL(LPAD(VVARTM,4,0),''0000''))<=(
                            SELECT VVARDT||NVL(LPAD(VVARTM,4,0),''0000'') 
                            AS ETADATE FROM SEALINER.ITP063 WHERE
                            VVSRVC=ACT_SERVICE_CODE AND
                            VVPCAL=DISCHARGE_PORT AND
                            VVVESS= ('''||P_I_V_VESSEL||''') AND
                            VVVOYN= ('''||P_I_V_VOYAGE||''') AND
                            VVVERS=99 AND
                            OMMISSION_FLAG IS NULL AND
                            VVFORL IS NOT NULL AND ROWNUM = 1  )
                            AND OMMISSION_FLAG IS NULL
                            AND VVFORL IS NOT NULL
                            AND (VVPCAL ,VVTRM1) NOT IN
                            (SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
                            VVSRVC=ACT_SERVICE_CODE AND
                            VVPCAL=DISCHARGE_PORT AND
                            VVVESS= ('''||P_I_V_VESSEL||''') AND
                            VVVOYN= ('''||P_I_V_VOYAGE||''') AND
                            VVTRM1=TO_TERMINAL AND
                            
                            VVVERS=99 AND
                            OMMISSION_FLAG IS NULL AND
                            VVFORL IS NOT NULL AND ROWNUM = 1 )
                            ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),''0000'')
                            DESC,VVPCSQ DESC)WHERE ROWNUM=1)                                                                PORT_OF_CALL_CODED,
                            (SELECT VVPCAL FROM (
                            SELECT VVPCAL FROM SEALINER.ITP063 WHERE 
                            VVVESS = ('''||P_I_V_VESSEL||''') AND VVVERS= 99 
                            AND ROWNUM = 1
                            AND(VVARDT||NVL(LPAD(VVARTM,4,0),''0000''))>=(
                            SELECT VVARDT||NVL(LPAD(VVARTM,4,0),''0000'')
                            AS ETADATE FROM SEALINER.ITP063 WHERE
                            VVSRVC=ACT_SERVICE_CODE AND
                            VVPCAL=DISCHARGE_PORT AND
                            VVVESS= ('''||P_I_V_VESSEL||''') AND
                            VVVOYN= ('''||P_I_V_VOYAGE||''') AND
                            
                            VVVERS=99 AND
                            OMMISSION_FLAG IS NULL AND
                            VVFORL IS NOT NULL AND ROWNUM = 1 )
                            AND OMMISSION_FLAG IS NULL
                            AND VVFORL IS NOT NULL
                            AND (VVPCAL ,VVTRM1) NOT IN
                            ( SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
                            VVSRVC =ACT_SERVICE_CODE AND
                            VVPCAL =DISCHARGE_PORT AND
                            VVVESS = ('''||P_I_V_VESSEL||''') AND
                            VVVOYN = ('''||P_I_V_VOYAGE||''') AND
                            VVTRM1=TO_TERMINAL AND
                            
                            VVVERS =99 AND
                            OMMISSION_FLAG IS NULL AND
                            VVFORL IS NOT NULL AND ROWNUM = 1)
                            ORDER BY VVARDT||NVL(LPAD(VVARTM,4,0),''0000''),VVPCSQ)
                            WHERE ROWNUM=1)                                                                                 NEXT_PORT_OF_CALL_CODED,
                            
                            ''MCLC''                                                                                        	MC_LOCATION_CUSTOMS,
                            
                            (SELECT  max(FK_UNNO)  FROM  RCLTBLS.DEX_BL_SPECIAL_CARGO_DTL SP 
                            , RCLTBLS.DEX_BL_COMMODITY CM WHERE CM.DN_SPECIAL_HNDL=''D1''
                            AND CM.FK_BL_NO=SP.FK_BL_NO 
                            AND CM.COMMODITY_SEQ=SP.COMMODITY_SEQ AND ROWNUM = 1)                                           UNO_CODE,
                            
                            (SELECT  FK_IMO_CLASS  FROM  RCLTBLS.DEX_BL_SPECIAL_CARGO_DTL SP
                            , RCLTBLS.DEX_BL_COMMODITY CM WHERE CM.DN_SPECIAL_HNDL=''D1''
                            AND CM.FK_BL_NO=SP.FK_BL_NO 
                            AND CM.COMMODITY_SEQ=SP.COMMODITY_SEQ
                            AND ROWNUM=1 AND CM.FK_BL_NO= SYBLNO AND ROWNUM=1)                                              IMDG_CODE,
                            
                            IDP10.AYISDT                                                                                    BL_DATE, 
                            ma_seq_no                                                                                       BL_VERSION,
                            IDP10.AYDEST                                                                                    DEL_VLS,
                            TO_TERMINAL                                                                                     DEPOT_VLS,
                            ''''															                                	ENBLOCK_MOVEMENT,
                            ''''															                                    CARRIER_NO,
                            ''''															                                	AGENCY_TYPE,
                            ''''															                                	INVOICE_VALUE_FC,
                            ''''  															                                INVOICE_VALUE_INR,
                            ''INR''															                                CURRENCY,
                            ''P''                                                                                           	MODEOF_TP_FEE,
                            ''''															                                	REMARK,
                            ''''															                               	 	SUB_LINE_NUMBER,
                            dn_pod                                                                                          PORT_OF_DESTINATION,
                            dn_pol                                                                                          PORT_OF_LOADING,
                            (SELECT A.PARTNER_VALUE 
                            FROM   EDI_TRANSLATION_DETAIL A 
                            WHERE  A.ETH_UID IN (SELECT ETH.ETH_UID 
                            FROM   EDI_TRANSLATION_HEADER ETH 
                            WHERE  ETH.STANDARD = ''EDIFACT'' 
                            AND ETH.CODE_SET = ''IGMPORT'' AND ROWNUM = 1) 
                            AND A.SEALINER_VALUE =  DN_PLD)                                                                 PORT_OF_DESCHARGED_CFS,
                            ''''                                                                                            	MULTIPAL_PAKAGES,
                            met_mt                                                                                          CBM,
                            ''''																                            	HIGHT_VALUE, 	
                            met_wt                                                                                          GROS_WEIGHT,
                            ''''																                            	UNIT, 		                
                            ''''																                            	VOLUME, 
                           (select ITEM_NUM from BL_ITEAM_NO_TEMP where  BL_NO = SYBLNO)                               ITEM_NUMBER,  
                            ( CASE 
                            WHEN AYMPOD = AYDEST THEN ''LC''
                            ELSE ''TI'' 
                            END )                                                                                           CARGO_MOVMNT, 
                            ''''                                                                                            	DPD_CODE, 
                            ''''                                                                                            	DPD_MOVEMENT,
                            ''''                                                                                            	DUTY_INR,
                            ''''                                                                                            	ROAD_CARR_CODE,
                            ''''                                                                                            	ROAD_TP_BOND_NO ,
                            ''SOC/COC''                                                                                     	BL_TYPE, 
                            ''FCL''                                                                                         	NE_CARGO_MOVMNT,				
                            
                            (CASE WHEN IDP5.DISCHARGE_PORT = (DISCHARGE_PORT) 
                            THEN ''DISCHARGED'' 
                            ELSE ''NOT''
                            END)                                                                                        FLAG_DISCHARGE,
                            (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT FLAG_DISCHARGE 
                            FROM IGM_BL_DETAILS_TWO WHERE  BL_NO_PK IN (SYBLNO)
                            AND rownum   =1),1,70) ),''DISCHARGED'')>0
                            THEN 
                            ''BL DISCHARGED''
                            ELSE
                            ''BL NOT DISCHARGED''
                            END)                                                                                  BL_DISCHARGE_STATUS,
                            (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT BL_DISCHARGE_STATUS
                            FROM IGM_BL_DETAILS_TWO WHERE  BL_NO_PK IN (SYBLNO)
                            AND rownum   =1),1,70) ),''BL NOT DISCHARGED'')>0
                            THEN 
                            ''ROB BOX''
                            ELSE
                            ''N''
                            END)                                                                                  			FLAG_ROB, 
                            (SELECT  NVL(STOWAGE_POSITION,NULL) FROM   VASAPPS.TOS_DL_BOOKED_DISCHARGE WHERE
                            FK_DISCHARGE_LIST_ID IN  
                            (SELECT PK_DISCHARGE_LIST_ID  FROM   VASAPPS.TOS_DL_discharge_LIST WHERE 
                            FK_VESSEL = ('''||P_I_V_VESSEL||''') AND FK_VOYAGE = ('''||P_I_V_VOYAGE||''') AND ROWNUM = 1
                            ) and FK_BOOKING_NO IN ( select FK_BOOKING_NO  from   RCLTBLS.BL_BOOKING_MAPPING WHERE
                            FK_BL_NO =  SYBLNO) and dn_equipment_no  IN 
                            (select  DN_CONTAINER_NO  FROM RCLTBLS.DEX_BL_CONTAINERS  
                            WHERE FK_BL_NO  IN (SYBLNO) AND rownum = 1)      FETCH FIRST 1 ROW ONLY)                       stowage_position,
                            ''''                                                                                            	HBL_NO,
                            (SELECT GST_STATE_CODE FROM SEALINER.ITP185 WHERE STCODE IN (
                            SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE  
                            CUSTOMER_TYPE =''C'' and FK_BL_NO = SYBLNO and ROWNUM =1)AND ROWNUM =1)                         	GST_STATE_CODE,
                            ''''		  DN_PLR,
                            ''''        DN_PLD,
                            ''''        ACCEPTANCE_NAME,
                            ''''        RECIEPT_NAME,
                            ''''        port_OF_call_name,
                            ''''        next_port_of_call_name,
                            (select sailing_date from rcltbls.dex_bl_routing where
                            FK_BL_NO = SYBLNO  AND ROWNUM = 1)                                                             MASTER_BL_DATE
                            
                            FROM   IDP005 IDP5 
                            INNER JOIN IDP010 IDP10  ON IDP5.SYBLNO = IDP10.AYBLNO
                            left OUTER JOIN dex_bl_commodity_temp dbct ON dbct.MBL_NO = IDP5.SYBLNO
                            left OUTER JOIN dex_bl_header_temp dbht ON dbht.MBL_NO = IDP5.SYBLNO   
                            left OUTER JOIN dex_bl_containers_temp dblct ON dblct.MBL_NO = IDP5.SYBLNO
                            WHERE  
                            IDP5.ACT_VESSEL_CODE = ('''||P_I_V_VESSEL||''')
                            AND IDP5.ACT_VOYAGE_NUMBER = ('''||P_I_V_VOYAGE||''')';
                        
                        
            BL_NO_QRY:=     'AND IDP5.SYBLNO in ('||P_I_V_BL||')'; 
                     
            EXECUTE IMMEDIATE  V_SQL||BL_NO_QRY;   commit; 
            
     END RCL_IGM_SAVE_UNFETCHED_BL_DATA_SUSHIL; 
     
    PROCEDURE RCL_IGM_GET_SAVE_BL_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
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
        
          P_O_V_ERROR:='000000';    

         OPEN P_O_REFIGMTABFIND FOR 
                SELECT
                    bl_no_pk       AS bl_no,
                    service,
                    vessel,
                    voyage,
                    pol_terminal,
                    del_vls,
                    depot_vls,
                    pod,
                    '' pod_terminal,
                    bl_date,
                    '' bl_version,
                    '' submit_date_time,
                    consolidated_indicator,
                    bl_type,
                    previous_declaration,
                    consolidator_pan,
                    cin_type,
                    mcin,
                    csn_submitted_type,
                    csn_submitted_by,
                    csn_reporting_type,
                    csn_site_id,
                    csn_number,
                    csn_date,
                    previous_mcin,
                    split_indicator,
                    number_of_packages,
                    type_of_package,
                    first_port_of_entry_last_port_of_departure,
                    type_of_cargo,
                    split_indicator_list,
                    port_of_acceptance,
                    port_of_receipt,
                    ucr_type,
                    ucr_code,
                    port_of_acceptance_name,
                    port_of_receipt_name,
                    pan_of_notified_party,
                    unit_of_weight,
                    gross_volume,
                    unit_of_volume,
                    cargo_item_sequence_no,
                    cargo_item_description,
                    container_weight,
                    number_of_packages_hid,
                    type_of_packages_hid,
                    port_of_call_sequence_number,
                    port_of_call_coded,
                    next_port_of_call_coded,
                    mc_location_customs,
                    uno_code,
                    imdg_code,
                    '' nhava_sheva_eta,
                    '' final_place_delivery,
                    '' packages,
                    '' cfs_name,
                    '' mbl_no,
                    '' hbl_no,
                    '' from_item_no,
                    '' to_item_no,
                    '' srl_no,
                    cargo_movmnt   cargo_movmnt,
                    '' line_code,
                                    --janmejaya(26-05-2021)
                    enblock_movement,
                    carrier_no,
                    agency_type,
                    invoice_value_fc,
                    invoice_value_inr,
                    currency,
                    modeof_tp_fee,
                    remark,
                    sub_line_number,
                    port_of_destination,
                    port_of_loading,
                    port_of_descharged_cfs,
                    multipal_pakages,
                    cbm,
                    hight_value,
                    gros_weight,
                    unit,
                    volume,
                    item_number,
                    dpd_code,
                    dpd_movement,
                    duty_inr,
                    road_carr_code,
                    road_tp_bond_no,
                    ne_cargo_movmnt,
                    '' CONSIGNEE_NAME,
                       (select FLAG_DG from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO = P_I_V_BL and rownum =1 ) flag_dg,
                        (select DN_COMMODITY_CODE from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO = P_I_V_BL and rownum =1) COMMODITY_CODE,
                        (select DN_PACKAGE_KIND from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO = P_I_V_BL and rownum =1) PACKAGE_KIND,
                        (select COMMODITY_SEQ from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO = P_I_V_BL and rownum =1) COMMODITY_SEQ ,
                    (select METRIC_MSMT from  RCLTBLS.dex_bl_containers
                        WHERE FK_BL_NO =  P_I_V_BL AND rownum   =1)  cargo_msmt,
                    
                     FLAG_DISCHARGE,
                     FLAG_ROB,
                     BL_DISCHARGE_STATUS,
                    STOWAGE_POSITION,
                    FK_HOUSE_BL_NO,
                    (SELECT GST_STATE_CODE FROM SEALINER.ITP185 WHERE STCODE IN (
                    SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE FK_BL_NO =P_I_V_BL AND CUSTOMER_TYPE ='S' AND ROWNUM = 1) and rownum =1)     GST_STATE_CODE,
                    DN_PLR,
                    DN_PLD,
                    ACCEPTANCE_NAME,
                    RECIEPT_NAME,
                    next_port_of_call_name,
                    port_OF_call_name,
                     (select sailing_date from rcltbls.dex_bl_routing where FK_BL_NO IN (P_I_V_BL) and ROWNUM = 1)   MASTER_BL_DATE,
                   (SELECT  count(FK_HOUSE_BL_NO) FROM  RCLTBLS.DEX_BL_HEADER h WHERE PK_BL_NO IN 
                   (SELECT PK_BL_NO  FROM  RCLTBLS.DEX_BL_HEADER WHERE FK_PART_OF_BL_NO IN (P_I_V_BL) and rownum =1 )) HBLCOUNT
                     
                FROM
                    IGM_BL_DETAILS_TWO idp5
                WHERE
                    ( p_i_v_service IS NULL
                      OR idp5.service = p_i_v_service )
                    AND ( p_i_v_vessel IS NULL
                          OR idp5.vessel = p_i_v_vessel )
                    AND ( p_i_v_voyage IS NULL
                          OR idp5.voyage = p_i_v_voyage )
                    AND ( p_i_v_bl IS NULL
                          OR idp5.bl_no_pk = p_i_v_bl );

    END RCL_IGM_GET_SAVE_BL_DATA;	  


    PROCEDURE RCL_IGM_GET_SAVE_BL_DATA_NEW(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
        P_I_V_POD          VARCHAR2, 
        P_I_V_BL           VARCHAR2 DEFAULT NULL,
        P_I_V_BL_COUNT      VARCHAR2 DEFAULT NULL,
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
        P_O_V_ERROR        OUT VARCHAR2
        ) 
        
            IS 
            V_SQL   VARCHAR2(32000); 
            V_SQL_CNDTN  VARCHAR2(4000);
            BEGIN   
    
                P_O_V_ERROR:='000000';    
    
       
    
                V_SQL:='SELECT
                        bl_no_pk      bl_no,
                        bl_date, 
                        service,
                        vessel,
                        voyage,
                        '''' direction,		
                        pol_terminal,
                        del_vls,
                        depot_vls,
                        pod,
                        '''' pod_terminal,
                        '''' bl_version,
                        consolidated_indicator,
                        bl_type,
                        previous_declaration,
                        consolidator_pan,
                        cin_type,
                        mcin,
                        csn_submitted_type,
                        csn_submitted_by,
                        csn_reporting_type,
                        csn_site_id,
                        csn_number,
                        csn_date,
                        previous_mcin,
                        split_indicator,
                        item_number,
                        enblock_movement,	
                        carrier_no,
                        agency_type,
                        invoice_value_fc,
                        invoice_value_inr,
                        currency,
                        '''' submit_date_time,
                        modeof_tp_fee,
                        remark,
                        sub_line_number,
                        port_of_destination,
                        port_of_loading,
                        port_of_descharged_cfs,
                        multipal_pakages,
                        cbm,
                        hight_value,
                        gros_weight,
                        unit,
                        volume,
                        cargo_movmnt   cargo_movmnt,
                        number_of_packages,
                        type_of_package,
                        first_port_of_entry_last_port_of_departure,
                        type_of_cargo,
                        split_indicator_list,
                        port_of_acceptance,
                        port_of_receipt,
                        ucr_type,
                        ucr_code,
                        port_of_acceptance_name,
                        port_of_receipt_name,
                        pan_of_notified_party,
                        unit_of_weight,
                        gross_volume,
                        unit_of_volume,
                        cargo_item_sequence_no,
                        cargo_item_description,
                        number_of_packages_hid,
                        type_of_packages_hid,
                        port_of_call_sequence_number,
                        PORT_OF_CALL_CODED,                      
                        next_port_of_call_coded,                       
                        mc_location_customs,
                        uno_code,
                        imdg_code,
                        container_weight,	
                        '''' nhava_sheva_eta,
                        '''' final_place_delivery,
                        '''' packages,
                        '''' cfs_name,
                        '''' mbl_no,
                        '''' hbl_no,
                        '''' from_item_no,
                        '''' to_item_no,
                        '''' srl_no,
                        '''' line_code,
                        dpd_code,
                        dpd_movement,
                        duty_inr,
                        road_carr_code,
                        road_tp_bond_no,
                        ne_cargo_movmnt,
                        (SELECT 
                        CONSIGNEE_NAME
                        from IGM_CONSIGNEE_TABLE_TWO   
                        WHERE   PK_BL_NO  IN (bl_no_pk) and rownum =1) CONSIGNEE_NAME,
                        
                        (select FLAG_DG from RCLTBLS.dex_bl_commodity 
                        WHERE FK_BL_NO IN (bl_no_pk) and rownum =1) flag_dg,
                        (select DN_COMMODITY_CODE from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO IN (bl_no_pk) and rownum =1) COMMODITY_CODE,
                         (select DN_PACKAGE_KIND from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO IN (bl_no_pk) and rownum =1) PACKAGE_KIND,
                        (select COMMODITY_SEQ from RCLTBLS.dex_bl_commodity WHERE FK_BL_NO IN (bl_no_pk) and rownum =1)  COMMODITY_SEQ,
                        
                        (select METRIC_MSMT from  RCLTBLS.dex_bl_containers
                        WHERE FK_BL_NO  IN (bl_no_pk) AND rownum =1)  cargo_msmt,
                        
                         FLAG_DISCHARGE,
                         BL_DISCHARGE_STATUS,
                       FLAG_ROB,
                       DN_PLR,
                       RECIEPT_NAME,
                       DN_PLD,
                       GST_STATE_CODE,
                       ACCEPTANCE_NAME,
                       STOWAGE_POSITION, 
                       next_port_of_call_name,
                       port_OF_call_name,
                      (select sailing_date from rcltbls.dex_bl_routing where FK_BL_NO  IN (bl_no_pk) and ROWNUM = 1)   MASTER_BL_DATE,
                      (SELECT  count(FK_HOUSE_BL_NO) FROM  RCLTBLS.DEX_BL_HEADER h WHERE PK_BL_NO IN (
                      (SELECT PK_BL_NO  FROM  RCLTBLS.DEX_BL_HEADER WHERE FK_PART_OF_BL_NO = bl_no_pk and rownum = 1)))   HBLCOUNT
                        
                      
                        FROM  IGM_BL_DETAILS_TWO IDP5
                        WHERE 
                        IDP5.VESSEL = ('''||P_I_V_VESSEL||''')
                        AND IDP5.VOYAGE = ('''||P_I_V_VOYAGE||''')
                        AND IDP5.BL_NO_PK in ('||P_I_V_BL||')'; 
    
            OPEN P_O_REFIGMTABFIND FOR V_SQL;
    
    END RCL_IGM_GET_SAVE_BL_DATA_NEW;	


PROCEDURE RCL_IGM_GET_BL_DATA_MASTER_SAVE_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR,  
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
            V_SQL   VARCHAR2(32000); 
            V_SQL_CNDTN  VARCHAR2(4000); 
            BEGIN   
    
                BL_COUNT := 0; 
                SELECT COUNT(*) 
                INTO   BL_COUNT 
                FROM   IGM_VESSEL_VOYAGE_DETAILES rid 
               WHERE ( P_I_V_SERVICE IS NULL
                              OR SERVICE = P_I_V_SERVICE )
                            AND ( P_I_V_VESSEL IS NULL
                                  OR VESSEL = P_I_V_VESSEL )
                            AND ( P_I_V_VOYAGE IS NULL
                                  OR VOYAGE = P_I_V_VOYAGE )
                            AND ( P_I_V_POD IS NULL
                                  OR POD = P_I_V_POD )
                            AND ( P_I_V_TO_DATE IS NULL
                                  OR BL_DATE <= P_I_V_TO_DATE )
                            AND ( P_I_V_BL_STATUS IS NULL
                                  OR BL_STATUS = P_I_V_BL_STATUS )
                            AND ( P_I_V_POL IS NULL
                                  OR POL = P_I_V_POL )
                            AND ( P_I_V_POL_TERMINAL IS NULL
                                  OR POL_TERMINAL = P_I_V_POL_TERMINAL )
                            AND ( P_I_V_DEL IS NULL
                                  OR DEL_VLS = P_I_V_DEL )
                            AND ( P_I_V_DEPOT IS NULL
                                  OR DEPOT_VLS = P_I_V_DEPOT );                   
                
                SELECT TO_TERMINAL INTO TEMP_POD_TERMINAL FROM IDP005 WHERE ACT_VESSEL_CODE=P_I_V_VESSEL
                AND ACT_VOYAGE_NUMBER=P_I_V_VOYAGE AND LOAD_PORT=P_I_V_POL AND ACT_SERVICE_CODE=P_I_V_SERVICE AND ROWNUM=1;
                
                P_O_V_ERROR:='000000';    
                IF BL_COUNT = 0 THEN   
                
                    OPEN P_O_REFIGMTABFIND FOR 
                    SELECT SYBLNO                       BL_NO, 
                    IDP10.AYISDT                        BL_DATE,
                    ACT_VESSEL_CODE                     VESSEL, 
                    ACT_VOYAGE_NUMBER                   VOYAGE, 
                    ''                                  ITEM_NUMBER, 
                    ( CASE 
                    WHEN AYMPOD = AYDEST THEN 'LC' 
                    ELSE 'TI' 
                    END )                               CARGO_MOVMNT, 
                    DISCHARGE_PORT                      POD,
                    IDP5.LOAD_PORT                      POL,
                    (select SOC_COC 
                    from DEX_BL_Header
                    where PK_BL_NO = SYBLNO)            BL_TYPE, -----
                    ( SELECT 
                    CUSTOMER_NAME as CONSIGNEE_NAME
                    FROM RCLTBLS.DEX_BL_CUSTOMERS 
                    WHERE  CUSTOMER_TYPE ='C'
                    and FK_BL_NO IN (SYBLNO))   CONSIGNEE_NAME,
                    
                    (SELECT  count(FK_HOUSE_BL_NO) FROM  RCLTBLS.DEX_BL_HEADER h WHERE PK_BL_NO IN (
                    (SELECT PK_BL_NO  FROM  RCLTBLS.DEX_BL_HEADER WHERE FK_PART_OF_BL_NO = SYBLNO))) HBLCOUNT,
                    
                     (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT FLAG_DISCHARGE FROM IGM_BL_DETAILS_TWO WHERE  BL_NO_PK IN (SYBLNO)
                    
                     AND rownum   =1),1,70) ),'DISCHARGED')>0
                         THEN 
                             'BL DISCHARGED'
                                 ELSE
                                    'BL NOT DISCHARGED'
                                    END) BL_DISCHARGE_STATUS
                    
                    FROM   IDP005 IDP5 
                    INNER JOIN IDP010 IDP10 
                    ON IDP5.SYBLNO = IDP10.AYBLNO 
                    WHERE   
                     IDP5.ACT_VESSEL_CODE = P_I_V_VESSEL
                     AND IDP5.ACT_VOYAGE_NUMBER = P_I_V_VOYAGE
                     AND (select TO_DATE(POL_ARRIVAL_DATE,'yyyy-mm-dd') from IDP005 where LOAD_PORT = P_I_V_POL
                     and ACT_VESSEL_CODE = P_I_V_VESSEL
                     AND ACT_VOYAGE_NUMBER =  P_I_V_VOYAGE and rownum = 1) >  to_date(IDP5.POL_ARRIVAL_DATE, 'yyyy-mm-dd');  
                    
                    ELSE
                    
                    OPEN P_O_REFIGMTABFIND FOR 
                    select
                    SYBLNO                              BL_NO, 
                    TO_CHAR( IDP10.AYISDT)     BL_DATE,
                    ACT_VESSEL_CODE                     VESSEL, 
                    ACT_VOYAGE_NUMBER                   VOYAGE, 
                    ''                                  ITEM_NUMBER, 
                    ( CASE 
                    WHEN AYMPOD = AYDEST THEN 'LC' 
                    ELSE 'TI' 
                    END )                               CARGO_MOVMNT, 
                    DISCHARGE_PORT                      POD,
                    IDP5.LOAD_PORT                      POL,
                    (select SOC_COC 
                    from DEX_BL_Header
                    where PK_BL_NO = SYBLNO)                           BL_TYPE, 
                     (CASE WHEN INSTR( UPPER(SUBSTR(( SELECT FLAG_DISCHARGE FROM IGM_BL_DETAILS_TWO WHERE  BL_NO_PK IN (SYBLNO)
                        AND rownum   =1),1,70) ),'DISCHARGED')>0
                         THEN 
                           'BL DISCHARGED'
                                 ELSE
                                    'BL NOT DISCHARGED'
                                    END) BL_DISCHARGE_STATUS,
                                    
                      (SELECT  count(FK_HOUSE_BL_NO) FROM  RCLTBLS.DEX_BL_HEADER h WHERE PK_BL_NO IN (
                       (SELECT PK_BL_NO  FROM  RCLTBLS.DEX_BL_HEADER WHERE FK_PART_OF_BL_NO = SYBLNO))) HBLCOUNT,
                       ( SELECT 
                    CUSTOMER_NAME as CONSIGNEE_NAME
                    FROM RCLTBLS.DEX_BL_CUSTOMERS 
                    WHERE  CUSTOMER_TYPE ='C'
                    and FK_BL_NO IN (SYBLNO) and rownum=1)   CONSIGNEE_NAME
                    
                    FROM   IDP005 IDP5 
                    INNER JOIN IDP010 IDP10 
                    ON IDP5.SYBLNO = IDP10.AYBLNO 
                    WHERE   
                     IDP5.ACT_VESSEL_CODE = P_I_V_VESSEL
                     AND IDP5.ACT_VOYAGE_NUMBER = P_I_V_VOYAGE
                     AND (select TO_DATE(POL_ARRIVAL_DATE,'yyyy-mm-dd') from IDP005 where LOAD_PORT = P_I_V_POL
                     and ACT_VESSEL_CODE = P_I_V_VESSEL
                     AND ACT_VOYAGE_NUMBER =  P_I_V_VOYAGE and rownum = 1) >  to_date(IDP5.POL_ARRIVAL_DATE, 'yyyy-mm-dd')
                AND SYBLNO not in  (SELECT bl_no_pk  FROM IGM_BL_DETAILS_TWO IDP5
                WHERE  ( P_I_V_SERVICE IS NULL 
                    OR IDP5.SERVICE = P_I_V_SERVICE )
                AND ( P_I_V_VESSEL IS NULL 
                OR IDP5.VESSEL = P_I_V_VESSEL ) 
                AND ( P_I_V_VOYAGE  IS NULL 
                OR IDP5.VOYAGE = P_I_V_VOYAGE ))
                    UNION
                    SELECT
                    bl_no_pk      bl_no,
                    bl_date,                
                    vessel,
                    voyage,
                    ITEM_NUMBER	,
                    CARGO_MOVMNT,
                    POD,
                    PORT_OF_LOADING as POD,
                    BL_TYPE,
                    BL_DISCHARGE_STATUS,
                    (SELECT  count(HBL_NO_PK) FROM  VASAPPS.IGM_HBL_DETAILS h WHERE MBL_NO_FK IN ( bl_no_pk)) HBLCOUNT, 
--                 (SELECT  count(FK_HOUSE_BL_NO) FROM  RCLTBLS.DEX_BL_HEADER h WHERE PK_BL_NO IN (
--                 (SELECT PK_BL_NO  FROM  RCLTBLS.DEX_BL_HEADER WHERE FK_PART_OF_BL_NO = bl_no_pk and rownum = 1  ))) HBLCOUNT
                    (SELECT 
                    CONSIGNEE_NAME
                    from IGM_CONSIGNEE_TABLE_TWO   
                    WHERE   PK_BL_NO  IN (bl_no_pk) and rownum=1) CUSTOMER_NAME

                    FROM IGM_BL_DETAILS_TWO IDP5
                    WHERE    
                ( P_I_V_SERVICE IS NULL 
                OR IDP5.SERVICE = P_I_V_SERVICE )
                AND ( P_I_V_VESSEL IS NULL 
                OR IDP5.VESSEL = P_I_V_VESSEL ) 
                AND ( P_I_V_VOYAGE IS NULL 
                OR IDP5.VOYAGE = P_I_V_VOYAGE ) 
                ORDER BY ITEM_NUMBER;
                
                END IF;
    
    END RCL_IGM_GET_BL_DATA_MASTER_SAVE_DATA;		      

    PROCEDURE RCL_IGM_SAVE_BL_DATA_SUSHIL(   
        P_I_V_BL	    VARCHAR2,                      
        P_I_V_BL_DTLS   IN      CLOB
        ) 
    
            IS
            V_SQL varchar(32000);
            BEGIN
    
                V_SQL:='DELETE FROM IGM_BL_DETAILS_TWO   WHERE BL_NO_PK in('|| P_I_V_BL||')';      
                EXECUTE IMMEDIATE  V_SQL; 
                COMMIT; 
                DELETE FROM IGM_DATA_SET_JSON;
    
                INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_BL_DTLS); COMMIT;      
                
                --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';
                
                --INSERT INTO rcm_error_log VALUES('IGM',SYSDATE,SYSDATE||'',SUBSTR(P_I_V_BL_DTLS, 1, 999),SUBSTR(P_I_V_BL_DTLS, 1000, 1999),SUBSTR(P_I_V_BL_DTLS, 2000, 2999),SUBSTR(P_I_V_BL_DTLS, 3000, 3999));commit;
                INSERT INTO IGM_BL_DETAILS_TWO 
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
                DEPOT_VLS,
                ENBLOCK_MOVEMENT,
                CARRIER_NO,	
                AGENCY_TYPE,
                INVOICE_VALUE_FC,
                INVOICE_VALUE_INR,
                CURRENCY,
                
                MODEOF_TP_FEE,
                REMARK,
                SUB_LINE_NUMBER,
                PORT_OF_DESTINATION,
                PORT_OF_LOADING,
                PORT_OF_DESCHARGED_CFS,
                MULTIPAL_PAKAGES,
                CBM,
                HIGHT_VALUE,				
                GROS_WEIGHT,				                
                UNIT,				                
                VOLUME,
                ITEM_NUMBER,
                CARGO_MOVMNT ,
                DPD_CODE ,
                DPD_MOVEMENT,
                DUTY_INR,
                ROAD_CARR_CODE,
                ROAD_TP_BOND_NO,      
                BL_TYPE,
                NE_CARGO_MOVMNT,
                
                FLAG_DISCHARGE,
                BL_DISCHARGE_STATUS,
                FLAG_ROB,
                
                STOWAGE_POSITION,
                FK_HOUSE_BL_NO,
                GST_STATE_CODE,
                DN_PLR,
                DN_PLD,
                ACCEPTANCE_NAME,
                RECIEPT_NAME,
                port_OF_call_name,
                next_port_of_call_name,
                MASTER_BL_DATE
                FROM  JSON_TABLE
                (
                (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( BL_NO_PK PATH '$.bl'
                , SERVICE PATH '$.service'
                , VESSEL PATH '$.vessel'
                , VOYAGE PATH '$.voyage'
                , POL_TERMINAL PATH '$.podTerminal'
                , POD PATH '$.pod'
                , CONSOLIDATED_INDICATOR PATH '$.consolidatedIndicator' 
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
                ,NUMBER_OF_PACKAGES PATH '$.total_number_of_packages'
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
                --janmejaya(26-05-2021)
                ,ENBLOCK_MOVEMENT PATH '$.enblockMovement'
                ,CARRIER_NO PATH '$.carrierNo'
                ,AGENCY_TYPE PATH '$.agencyType'
                ,INVOICE_VALUE_FC PATH '$.invoiceValueFc'
                ,INVOICE_VALUE_INR PATH '$.invoiceValueInr'
                ,CURRENCY PATH '$.currency'					 
                ,MODEOF_TP_FEE PATH '$.modeOfTpFee'
                ,REMARK PATH '$.remark'
                ,SUB_LINE_NUMBER PATH '$.subLineNumber'
                ,PORT_OF_DESTINATION PATH '$.portOfDestination'
                ,PORT_OF_LOADING PATH '$.portOfLoading'
                ,PORT_OF_DESCHARGED_CFS PATH '$.portOfDeschargedCfs'
                ,MULTIPAL_PAKAGES PATH '$.multipalPakages'
                ,CBM PATH '$.cbm'
                ,HIGHT_VALUE PATH '$.hightValue'
                ,GROS_WEIGHT PATH '$.grosWeight'
                ,UNIT PATH '$.unit'
                ,VOLUME PATH '$.volume'
                ,ITEM_NUMBER PATH '$.itemNumber'
                ,cargo_Movmnt PATH '$.cargoMovmnt'
                ,DPD_CODE PATH '$.dpdCode'
                ,DPD_MOVEMENT PATH '$.dpdMovement'
                ,DUTY_INR PATH '$.dutyInr'
                ,ROAD_CARR_CODE PATH '$.carrierNo'
                ,ROAD_TP_BOND_NO PATH '$.tpBondNo'
                ,BL_TYPE PATH '$.blType' 
                ,NE_CARGO_MOVMNT PATH '$.neCargoMovmnt'
                
                ,FLAG_DISCHARGE PATH '$.flag_discharge'
                ,BL_DISCHARGE_STATUS PATH '$.blDischargedStatus'
                ,FLAG_ROB PATH '$.flagRob'
                
                 ,STOWAGE_POSITION PATH '$.stowageExport',
                  FK_HOUSE_BL_NO PATH '$.houseBl',
                  GST_STATE_CODE PATH '$.gstStateCode',
                  DN_PLR PATH '$.dn_plr',
                  DN_PLD PATH '$.dn_pld',
                  ACCEPTANCE_NAME PATH '$.acceptanceName',
                  RECIEPT_NAME PATH '$.recieptName' 
                 ,port_OF_call_name PATH '$.port_of_call_name'
                 ,next_port_of_call_name PATH '$.next_port_of_call_name'
                 ,MASTER_BL_DATE PATH '$.masterBlDate') 
                );
    
                COMMIT;
    
    END RCL_IGM_SAVE_BL_DATA_SUSHIL;   

    PROCEDURE RCL_IGM_DELETE_BL_DATA(    
        P_I_V_BL	    VARCHAR2,                      
        P_I_V_BL_DTLS   IN      CLOB
        ) 
    
            IS
            V_SQL varchar(32000);
            BEGIN
    
            V_SQL:='DELETE FROM IGM_BL_DETAILS_TWO   WHERE BL_NO_PK in('|| P_I_V_BL||')';      
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
    
    END RCL_IGM_DELETE_BL_DATA;  

    PROCEDURE RCL_IGM_SAVE_CONSIGNEE_DATA(   
    P_I_V_BL	    VARCHAR2,
    P_I_V_CONSIGNEE_DTLS   IN      CLOB
    ) 
    
        IS
        V_SQL varchar(32000);
        BEGIN
    
        -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
            V_SQL:='DELETE FROM IGM_CONSIGNEE_TABLE_TWO  WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
    
            COMMIT; 
                       insert into sushiltest2 values ('deleted this saved bl'||P_I_V_BL);
        commit;
            DELETE FROM IGM_DATA_SET_JSON;
    
            INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_CONSIGNEE_DTLS); COMMIT;  
    
            --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';
    
            INSERT INTO IGM_CONSIGNEE_TABLE_TWO
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
            ,ZIP PATH '$.zip' )
            );
            COMMIT;
    
    END RCL_IGM_SAVE_CONSIGNEE_DATA;

      PROCEDURE RCL_IGM_SAVE_UNFETCHED_CONSIGNEE_DATA(    
                    P_I_V_BL	    VARCHAR2 
                    ) 
    
        IS
        V_SQL varchar2(32000);
        BEGIN
    
        -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
            V_SQL:='DELETE FROM IGM_CONSIGNEE_TABLE_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
    
    
           V_SQL:= 'INSERT INTO IGM_CONSIGNEE_TABLE_TWO
            (PK_BL_NO,
            CONSIGNEE_CODE,
            CONSIGNEE_NAME,
            ADDRESS_LINE_1,
            ADDRESS_LINE_2,
            ADDRESS_LINE_3,
            ADDRESS_LINE_4,
            CITY,
            STATE,
            DN_COUNTRY_CODE,
            ZIP)
            SELECT 
                        FK_BL_NO as PK_BL_NO,
						FK_CUSTOMER_CODE as CONSIGNEE_CODE,
						CUSTOMER_NAME as CONSIGNEE_NAME,
                        ADDRESS_LINE_1,
                        ADDRESS_LINE_2,
                        ADDRESS_LINE_3,
                        ADDRESS_LINE_4,
						CITY,
                        STATE,
                        DN_COUNTRY_CODE,
                        ZIP 
                FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE  CUSTOMER_TYPE =''C'' 
                and FK_BL_NO IN ('||P_I_V_BL||')';  
            EXECUTE IMMEDIATE  V_SQL;
            COMMIT;
    
    END RCL_IGM_SAVE_UNFETCHED_CONSIGNEE_DATA;

    PROCEDURE RCL_IGM_DELETE_CONSIGNEE_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_CONSIGNEE_DTLS   IN      CLOB
        )            
            IS
            V_SQL varchar(32000);
            BEGIN   
    
            V_SQL:='DELETE FROM IGM_CONSIGNEE_TABLE_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            
        COMMIT; 
        DELETE FROM IGM_DATA_SET_JSON; 
    
    END RCL_IGM_DELETE_CONSIGNEE_DATA;


    PROCEDURE RCL_IGM_SAVE_CONSIGNER_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_CONSIGNER_DTLS   IN      CLOB
        ) 
    
            IS
            V_SQL varchar(32000);
            BEGIN
                -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
                V_SQL:='DELETE FROM IGM_CONSIGNER_TABLE_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
                EXECUTE IMMEDIATE  V_SQL; 
                COMMIT; 
                DELETE FROM IGM_DATA_SET_JSON;
        
                INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_CONSIGNER_DTLS); COMMIT;                      
        
                --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';
        
                INSERT INTO IGM_CONSIGNER_TABLE_TWO
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
                ,ZIP PATH '$.zip')
                );
                COMMIT;
    
    END RCL_IGM_SAVE_CONSIGNER_DATA;	
    
       PROCEDURE RCL_IGM_SAVE_UNFETCHED_CONSIGNER_DATA(   
        P_I_V_BL	    VARCHAR2 
        ) 
    
            IS
            V_SQL varchar2(32000);
            BEGIN
    
                -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
                V_SQL:='DELETE FROM IGM_CONSIGNER_TABLE_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
                EXECUTE IMMEDIATE  V_SQL; 
                COMMIT; 
                 
        
             V_SQL :=   'INSERT INTO IGM_CONSIGNER_TABLE_TWO
                   (PK_BL_NO,
                    CONSIGNER_CODE,
                    CONSIGNER_NAME,
                    ADDRESS_LINE_1,
                    ADDRESS_LINE_2,
                    ADDRESS_LINE_3,
                    ADDRESS_LINE_4,
                    CITY,
                    STATE,
                    DN_COUNTRY_CODE,
                    ZIP)
                    SELECT  
                            FK_BL_NO as PK_BL_NO,
                            FK_CUSTOMER_CODE as CONSIGNER_CODE,
                            CUSTOMER_NAME as CONSIGNER_NAME,
                            ADDRESS_LINE_1,
                            ADDRESS_LINE_2,
                            ADDRESS_LINE_3,
                            ADDRESS_LINE_4,
                            CITY,
                            STATE,
                            DN_COUNTRY_CODE,
                            ZIP                            
                            FROM RCLTBLS.DEX_BL_CUSTOMERS 	
                            WHERE  CUSTOMER_TYPE =''S'' 
                            and FK_BL_NO IN ('||P_I_V_BL||')';  
                EXECUTE IMMEDIATE  V_SQL;            
                COMMIT;
    
    END RCL_IGM_SAVE_UNFETCHED_CONSIGNER_DATA;	 

    PROCEDURE RCL_IGM_DELETE_CONSIGNER_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_CONSIGNER_DTLS   IN      CLOB
        ) 
    
        IS
        V_SQL varchar(32000);
        BEGIN
    
            V_SQL:='DELETE FROM IGM_CONSIGNER_TABLE_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
    
    END RCL_IGM_DELETE_CONSIGNER_DATA;	


    PROCEDURE RCL_IGM_GET_MARKS_DESCRIPTION(
    P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
    P_I_V_BL            VARCHAR2 DEFAULT NULL,
    P_O_V_ERROR         OUT VARCHAR2)     
    
        IS 
        l_v_sql VARCHAR2(32000);    
        BEGIN   
        
               l_v_sql:='select
                        ''origninal'' as type,
                        RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.FK_BL_NO as FK_BL_NO,
                        DBMS_LOB.SUBSTR(RCLTBLS.DEX_BL_MARKS_NO.MARKS_NO, 4000, 1) as MARKS_NO ,
                        DBMS_LOB.SUBSTR(RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.DESCRIPTION, 4000, 1) as DESCRIPTION,
                        AYISDT as bldate,
                        ''''  as REMARK
                        from
                        RCLTBLS.DEX_BL_MARKS_NO
                        inner join RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC on
                        RCLTBLS.DEX_BL_MARKS_NO.DN_EQUIPMENT_NO = RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.DN_EQUIPMENT_NO
                        and RCLTBLS.DEX_BL_MARKS_NO.FK_BL_NO = RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.FK_BL_NO
                        inner join IDP010 on  RCLTBLS.DEX_BL_MARKS_NO.FK_BL_NO=IDP010.AYBLNO
                        where
                        RCLTBLS.DEX_BL_MARKS_NO.DN_EQUIPMENT_NO = ''XXXXXXXXX01''
                        and RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.FK_BL_NO in ('||P_I_V_BL||')';
    
    
        OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_MARKS_DESCRIPTION;


    PROCEDURE RCL_IGM_GET_SAVE_MARKS_DESCRIPTION(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)     
        
        IS 
        l_v_sql VARCHAR2(32000);   
        BEGIN   
    
               l_v_sql:='SELECT 
                        ''origninal''   type,
                        a.PK_BL_NO as fk_bl_no,
                        a.MARKS_NUMBER  as MARKS_NO,
                        a.DESCRIPTION   as DESCRIPTION,
                        a.REMARK        as REMARK,
                        BL_DATE        as bldate
                        FROM IGM_MARKS_NUMBER_DESCRIPTION_TWO a inner join IGM_BL_DETAILS_TWO b on  a.PK_BL_NO=b.BL_NO_PK                              
                        WHERE   PK_BL_NO IN ('||P_I_V_BL||')';
    
    
            OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_SAVE_MARKS_DESCRIPTION;

    PROCEDURE RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS   IN      CLOB
        ) 
    
            IS
            V_SQL varchar(32000);
            BEGIN
            
            -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
            V_SQL:='DELETE FROM IGM_MARKS_NUMBER_DESCRIPTION_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
            DELETE FROM IGM_DATA_SET_JSON;
            
            INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS); COMMIT;                      
            
            --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';
            
            INSERT INTO IGM_MARKS_NUMBER_DESCRIPTION_TWO
            SELECT 
            PK_BL_NO,
            MARKS_NUMBER ,
            DESCRIPTION ,
            REMARK
            FROM  JSON_TABLE
            (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
            COLUMNS ( PK_BL_NO PATH '$.blNO'
            , MARKS_NUMBER PATH '$.marksNumbers'
            , DESCRIPTION PATH '$.description'
            , REMARK PATH '$.droRemarks')
            );
            COMMIT;
    
    END RCL_IGM_SAVE_MARKS_NUMBER_DESCRIPTION_DATA;	  

    PROCEDURE RCL_IGM_SAVE_UNFETCHED_MARKS_NUMBER_DESCRIPTION_DATA(   
        P_I_V_BL	    VARCHAR2 
        ) 
    
            IS
            V_SQL varchar2(32000);
            BEGIN
             
            V_SQL:= 'DELETE FROM IGM_MARKS_NUMBER_DESCRIPTION_TWO   WHERE PK_BL_NO in ('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
            
            V_SQL:= 'INSERT INTO IGM_MARKS_NUMBER_DESCRIPTION_TWO
            (PK_BL_NO,
             MARKS_NUMBER ,
             DESCRIPTION ,
             REMARK)
           
            select
                       
                        RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.FK_BL_NO as PK_BL_NO,
                        DBMS_LOB.SUBSTR(RCLTBLS.DEX_BL_MARKS_NO.MARKS_NO, 4000, 1) as MARKS_NUMBER ,
                        DBMS_LOB.SUBSTR(RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.DESCRIPTION, 4000, 1) as DESCRIPTION,
                        ''''  as REMARK
                        from
                        RCLTBLS.DEX_BL_MARKS_NO
                        inner join RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC on
                        RCLTBLS.DEX_BL_MARKS_NO.DN_EQUIPMENT_NO = RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.DN_EQUIPMENT_NO
                        and RCLTBLS.DEX_BL_MARKS_NO.FK_BL_NO = RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.FK_BL_NO
                        inner join IDP010 on  RCLTBLS.DEX_BL_MARKS_NO.FK_BL_NO=IDP010.AYBLNO
                        where
                        RCLTBLS.DEX_BL_MARKS_NO.DN_EQUIPMENT_NO = ''XXXXXXXXX01''
                        and RCLTBLS.DEX_BL_COMM_EQUIPMENT_DESC.FK_BL_NO in ('||P_I_V_BL||')';  
            EXECUTE IMMEDIATE  V_SQL;
            COMMIT;
    
    END RCL_IGM_SAVE_UNFETCHED_MARKS_NUMBER_DESCRIPTION_DATA;

    PROCEDURE RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_MARKS_NUMBER_DESCRIPTION_DTLS   IN      CLOB
        ) 
    
            IS
            V_SQL varchar(32000);
            BEGIN
    
            V_SQL:='DELETE FROM IGM_MARKS_NUMBER_DESCRIPTION_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
    
    END RCL_IGM_DELETE_MARKS_NUMBER_DESCRIPTION_DATA;	  

    PROCEDURE RCL_IGM_MASTER_NODIFY_PARTY_DATA(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL             VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)     
        
        AS 
        l_v_sql VARCHAR2(32000);
        BEGIN   
               l_v_sql:='SELECT 
                        ''origninal''   type,
                        FK_BL_NO as FK_BL_NO,
                        FK_CUSTOMER_CODE as CUSTOMER_CODE,   
                        CUSTOMER_NAME, 
                        ADDRESS_LINE_1,
                        ADDRESS_LINE_2,
                        ADDRESS_LINE_3, 
                        ADDRESS_LINE_4, 
                        CITY, STATE, 
                        DN_COUNTRY_CODE, 
                        ZIP,
                        '''' as NOTIFY2,
                        (SELECT STATE_NAME FROM RCLTBLS.CAM_STATE WHERE PK_STATE_CODE IN 
                        (SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE FK_BL_NO = FK_BL_NO AND ROWNUM = 1) )    STATE_NAME,
                       (SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''1''  AND rownum   =1) NOTIFY_NAME,
                        
                        (select DISCHARGE_PORT from IDP005 where SYBLNO IN (FK_BL_NO) AND rownum   =1) PORT_OF_DISCHARGE,
                        
                        (SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO) AND CYRCTP   = ''1'' AND rownum   =1) CONSIGNEE_CHECK_BOX, 
                        
                        (SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND rownum   =1
                            ))                                                CONSIGNEE_FWR, 
                        
                                       CASE WHEN  (INSTR( UPPER(    SUBSTR(( select DISCHARGE_PORT from IDP005 where SYBLNO IN (FK_BL_NO)
                         AND rownum   =1
                          ),1,2) ),''IN'')>0 AND
                          
                          INSTR( UPPER(    SUBSTR(( select DN_COUNTRY_CODE  FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE =''C'' AND  FK_BL_NO IN (FK_BL_NO)
                           AND rownum   =1
                          ),1,2) ),''IN'')>0) THEN  
                         
                        (CASE WHEN 
                         INSTR( UPPER(    SUBSTR((SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO) AND CYRCTP   = ''C'' 
                          AND rownum   =1
                          ),1,70) ),''Y'')>0 OR
                          INSTR( UPPER(    SUBSTR((SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO) AND CYRCTP   = ''C''  
                           AND rownum   =1
                          ),1,70) ),''N'')>0 AND 
                          
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''BANK'')>0 OR 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER'')>0 or 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO THE ORDER OF'')>0 or
                                
                                INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER OF'')>0    THEN  
                          
                          (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1)
                            ) 
                            WHEN    INSTR( UPPER(    SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND rownum   =1)
                          ),1,70) ),''FWR'')>0    THEN  
                          
                          (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1)
                            )
                            
                            ELSE 
                          (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP  = ''C''
                            AND rownum   =1)
                          )       
                           END )
                           
                           ELSE 
                           
                           (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND CYRCTP   = ''C''
                            AND rownum   =1)
                            )
                            END NOTIFY_IEC,
              
            CASE WHEN  (INSTR( UPPER(    SUBSTR(( select DISCHARGE_PORT from IDP005 where SYBLNO IN (FK_BL_NO)  AND rownum   =1
              ),1,2) ),''IN'')>0 AND
              
              INSTR( UPPER(    SUBSTR(( select DN_COUNTRY_CODE  FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE = ''C'' AND   FK_BL_NO IN (FK_BL_NO)  AND rownum   =1
              ),1,2) ),''CN'')>0) THEN  
             
            (CASE WHEN 
             (INSTR( UPPER(    SUBSTR((SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO) AND CYRCTP  = ''C''  AND rownum   =1
              ),1,70) ),''Y'')>0 OR
              INSTR( UPPER(    SUBSTR((SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO) AND CYRCTP   = ''C''  AND rownum   =1 
              ),1,70) ),''N'')>0) AND 
              
              INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C''
              AND rownum   =1
              ),1,70) ),''BANK'')>0 OR 
              INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C''
              AND rownum   =1
              ),1,70) ),''TO ORDER'')>0 or 
              INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)  AND CYRCTP   = ''C''
              AND rownum   =1
              ),1,70) ),''TO THE ORDER OF'')>0 or
                    
                        INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (FK_BL_NO)   AND CYRCTP  = ''C''
              AND rownum   =1
              ),1,70) ),''TO ORDER OF'')>0    THEN  
              
              (SELECT FEDERAL_TAX_ID
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (FK_BL_NO)
                AND CYRCTP   = ''1''
                AND rownum   =1
                )
                ) 
                WHEN    INSTR( UPPER( SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (FK_BL_NO)
                            AND rownum   =1)
                            
              ),1,70) ),''FWR'')>0    THEN  
              
              (SELECT FEDERAL_TAX_ID
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (FK_BL_NO)
                AND CYRCTP   = ''C''
                AND rownum   =1)
                )
                
                ELSE 
              (SELECT FEDERAL_TAX_ID
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (FK_BL_NO)
                AND CYRCTP  = ''C''
                AND rownum   =1)
              ) 
              
               END )
               
               ELSE 
               
               (SELECT FEDERAL_TAX_ID
              FROM RCLTBLS.CAM_CUSTOMER
              WHERE PK_CUSTOMER_CODE=
                (SELECT CYCUST
                FROM sealiner.IDP030
                WHERE CYBLNO IN (FK_BL_NO)
                AND CYRCTP   = ''C''
                AND rownum   =1)
                )
                END NOTIFY_PAN
                        FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE 
                        CUSTOMER_TYPE IN(''N'',''1'') 
                        AND FK_BL_NO IN ('||P_I_V_BL||')';  -- 2
    
         OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    
    END RCL_IGM_MASTER_NODIFY_PARTY_DATA;

    PROCEDURE RCL_IGM_GET_SAVE_NOTIFYPARTY(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2)     
        
            IS  
            l_v_sql VARCHAR2(32000);   
            BEGIN   
            
                l_v_sql:='  SELECT 
                ''origninal''   type,
                PK_BL_NO as FK_BL_NO,
                CUSTOMER_CODE ,
                CUSTOMER_NAME,
                ADDRESS_LINE_1,
                ADDRESS_LINE_2,
                ADDRESS_LINE_3,
                ADDRESS_LINE_4,
                CITY,
                STATE,
                DN_COUNTRY_CODE,
                ZIP,
                NOTIFY2,
               (SELECT STATE_NAME FROM RCLTBLS.CAM_STATE WHERE PK_STATE_CODE IN 
               (SELECT STATE FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE FK_BL_NO = FK_BL_NO AND ROWNUM = 1) )    STATE_NAME,  
                 (SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''1''  AND rownum  =1) NOTIFY_NAME,
                 
                 (select DISCHARGE_PORT from IDP005 where SYBLNO IN (PK_BL_NO) AND rownum   =1) PORT_OF_DISCHARGE, 
                 
                 (SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO) AND CYRCTP   = ''1'' AND rownum   =1) CONSIGNEE_CHECK_BOX, 
                 
                 (SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (PK_BL_NO)
                            AND rownum   =1
                            ))                                                CONSIGNEE_FWR,  

                    (CASE WHEN 
                            INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''1''
                          AND rownum   =1
                          ),1,70) ),''SAME AS CONSIGNEE'')>0 OR 
                          
                        INSTR( UPPER( SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (PK_BL_NO) 
                            AND CYRCTP   = ''1''
                            AND rownum   =1)
                            ),1,70) ),''FWR'')>0  then 
                            (SELECT SIC_CODE
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1)
                            )
                     when  (INSTR( UPPER( SUBSTR(( select DISCHARGE_PORT from IDP005 where SYBLNO  IN (PK_BL_NO)
                         AND rownum   =1
                          ),1,2) ),''IN'')>0 AND
                          
                          INSTR( UPPER(    SUBSTR(( select DN_COUNTRY_CODE  FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE =''C'' AND FK_BL_NO IN (PK_BL_NO)
                           AND rownum   =1
                          ),1,2) ),''IN'')>0) THEN
                (CASE WHEN INSTR( UPPER( SUBSTR((SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO) AND CYRCTP   = ''C''
                           AND rownum   =1
                          ),1,70) ),''Y'')>0 THEN 
                          (CASE WHEN
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''1''
                          AND rownum   =1
                          ),1,70) ),''SAME AS CONSIGNEE'')>0 OR 
                          
                        INSTR( UPPER( SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (PK_BL_NO)
                            AND rownum   =1)
                            ),1,70) ),''FWR'')>0  then
                            
                               (SELECT SIC_CODE
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1)
                            )
                    WHEN INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''BANK'')>0 then 
                          (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (PK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1)
                            )  
                            
                     ELSE
                            (SELECT SIC_CODE
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1)
                            ) 
                            END) 
                          
                    ELSE
                    (CASE WHEN INSTR( UPPER(    SUBSTR((SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO) AND CYRCTP   =''C''   
                           AND rownum   =1
                          ),1,70) ),''N'')>0 THEN 
                        (CASE WHEN   INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''SAME AS CONSIGNEE'')>0 OR 
                          
                           INSTR( UPPER( SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (PK_BL_NO)
                            AND rownum   =1)
                            ),1,70) ),''FWR'')>0  then
                             (SELECT SIC_CODE
                              FROM RCLTBLS.CAM_CUSTOMER
                              WHERE PK_CUSTOMER_CODE=
                             (SELECT CYCUST
                                 FROM sealiner.IDP030
                                 WHERE CYBLNO  IN (PK_BL_NO)
                                 AND CYRCTP  =''C''
                                 AND rownum   =1)
                                 )
                     WHEN INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''BANK'')>0 OR 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER'')>0 or 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO THE ORDER OF'')>0 or
                                
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER OF'')>0    THEN  
                          
                          (SELECT SIC_CODE
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (PK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1)
                            ) 
 
                            ELSE
                             (SELECT SIC_CODE
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1)
                            )
                            
                            END)
                          
                          
                          ELSE
                          
                          (SELECT SIC_CODE
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1))
                          END)
                       
                      END)    
                    ELSE
                            (SELECT SIC_CODE
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1))
                            
                             END   )  NOTIFY_IEC,
                             
                 (CASE WHEN 
                            INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)    AND CYRCTP   = ''1''
                          AND rownum   =1
                          ),1,70) ),''SAME AS CONSIGNEE'')>0 OR 
                          
                        INSTR( UPPER( SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (PK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1)
                            ),1,70) ),''FWR'')>0  then 
                            
                            (SELECT FEDERAL_TAX_ID
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1)
                            )
                            
                     when  (INSTR( UPPER( SUBSTR(( select DISCHARGE_PORT from IDP005 where SYBLNO  IN (PK_BL_NO)
                         AND rownum   =1
                          ),1,2) ),''IN'')>0 AND
                          
                          INSTR( UPPER(    SUBSTR(( select DN_COUNTRY_CODE  FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE =''C''
                          AND  FK_BL_NO  IN (PK_BL_NO)
                           AND rownum   =1
                          ),1,2) ),''IN'')>0) THEN
                (CASE WHEN INSTR( UPPER( SUBSTR((SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO) AND CYRCTP   = ''C''  
                           AND rownum   =1
                          ),1,70) ),''Y'')>0 THEN 
                          (CASE WHEN
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO)   AND CYRCTP   = ''1'' 
                          AND rownum   =1
                          ),1,70) ),''SAME AS CONSIGNEE'')>0 OR 
                          
                        INSTR( UPPER( SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (PK_BL_NO)
                            AND rownum   =1)
                            ),1,70) ),''FWR'')>0  then
                            
                               (SELECT FEDERAL_TAX_ID
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1)
                            )
                    WHEN INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''BANK'')>0 then 
                          (SELECT FEDERAL_TAX_ID
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (PK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1)
                            )  
                            
                     ELSE
                            (SELECT FEDERAL_TAX_ID
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1)
                            ) 
                            END) 
                          
                    ELSE
                    (CASE WHEN INSTR( UPPER(    SUBSTR((SELECT  TO_ORDER_FLAG    FROM sealiner.IDP030   WHERE CYBLNO IN (PK_BL_NO) AND CYRCTP   = ''C''  
                           AND rownum   =1
                          ),1,70) ),''N'')>0 THEN 
                        (CASE WHEN   INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''SAME AS CONSIGNEE'')>0 OR 
                          
                           INSTR( UPPER( SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (''XMNCB17003640'')
                            AND rownum   =1)
                            ),1,70) ),''FWR'')>0  then
                            
                             (SELECT FEDERAL_TAX_ID
                              FROM RCLTBLS.CAM_CUSTOMER
                              WHERE PK_CUSTOMER_CODE=
                             (SELECT CYCUST
                                 FROM sealiner.IDP030
                                 WHERE CYBLNO  IN (PK_BL_NO)
                                 AND CYRCTP  = ''C''
                                 AND rownum   =1)
                                 )
                     WHEN INSTR( UPPER(SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''BANK'')>0 OR 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER'')>0 or 
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)   AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO THE ORDER OF'')>0 or
                                
                          INSTR( UPPER(    SUBSTR((SELECT CYNAME    FROM sealiner.IDP030   WHERE CYBLNO  IN (PK_BL_NO)  AND CYRCTP   = ''C''
                          AND rownum   =1
                          ),1,70) ),''TO ORDER OF'')>0    THEN  
                          
                          (SELECT FEDERAL_TAX_ID
                          FROM RCLTBLS.CAM_CUSTOMER
                          WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO  IN (PK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1)
                            ) 
                  WHEN    INSTR( UPPER(    SUBSTR(( SELECT  CUSTOMER_TYPE FROM RCLTBLS.CAM_CUSTOMER WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                            FROM sealiner.IDP030
                            WHERE CYBLNO IN (PK_BL_NO)
                            AND CYRCTP   = ''1''
                            AND rownum   =1
                            )
                          ),1,70) ),''FWR'')>0  THEN 
                          
                             (SELECT FEDERAL_TAX_ID
                              FROM RCLTBLS.CAM_CUSTOMER
                              WHERE PK_CUSTOMER_CODE=
                                (SELECT CYCUST
                                FROM sealiner.IDP030
                                WHERE CYBLNO IN (PK_BL_NO)
                                AND CYRCTP   = ''1''
                                AND rownum   =1)
                                )

                            ELSE
                             (SELECT FEDERAL_TAX_ID
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1)
                            )
                            
                            END)

                          ELSE                         
                          (SELECT FEDERAL_TAX_ID
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1))
                          END) 
                       
                      END)   
                      
                    ELSE 
                            (SELECT FEDERAL_TAX_ID
                             FROM RCLTBLS.CAM_CUSTOMER
                             WHERE PK_CUSTOMER_CODE=
                            (SELECT CYCUST
                             FROM sealiner.IDP030
                             WHERE CYBLNO  IN (PK_BL_NO)
                             AND CYRCTP  = ''C''
                             AND rownum   =1))
                            
                             END   )  NOTIFY_PAN
                
                from IGM_NODIFY_PARTY_IGM_TWO   
                WHERE   PK_BL_NO IN ('||P_I_V_BL||')';
                
          OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    
    END RCL_IGM_GET_SAVE_NOTIFYPARTY;

    PROCEDURE RCL_IGM_SAVE_NODIFY_PARTY_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_NODIFY_PARTY_DTLS   IN      CLOB
        ) 
    
            IS
            V_SQL varchar(32000);
            BEGIN
    
    -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
            V_SQL:='DELETE FROM IGM_NODIFY_PARTY_IGM_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
            DELETE FROM IGM_DATA_SET_JSON;
    
            INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_NODIFY_PARTY_DTLS); COMMIT;                      
    
            --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';
    
            INSERT INTO IGM_NODIFY_PARTY_IGM_TWO
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
            , CUSTOMER_CODE PATH '$.costumerCode'
            , CUSTOMER_NAME PATH '$.costumerName' 
            , ADDRESS_LINE_1 PATH '$.addressLine1'
            , ADDRESS_LINE_2 PATH '$.addressLine2'
            , ADDRESS_LINE_3 PATH '$.addressLine3'
            , ADDRESS_LINE_4 PATH '$.addressLine4' 
            ,CITY PATH '$.city'
            ,STATE PATH '$.state' 
            ,DN_COUNTRY_CODE PATH '$.countryCode'
            ,ZIP PATH '$.zip' 
            ,notify2 PATH '$.notify2')
            );
            COMMIT;
    
    END RCL_IGM_SAVE_NODIFY_PARTY_DATA;	
    
     PROCEDURE RCL_IGM_SAVE_UNFETCHED_NODIFY_PARTY_DATA(   
        P_I_V_BL	    VARCHAR2 
        ) 
    
            IS
            V_SQL varchar2(32000);
            BEGIN
    
    -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
            V_SQL:='DELETE FROM IGM_NODIFY_PARTY_IGM_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
       
          V_SQL:=  'INSERT INTO IGM_NODIFY_PARTY_IGM_TWO
            (PK_BL_NO,
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
            notify2 ) 
            SELECT 
                        
                        FK_BL_NO as PK_BL_NO,
                        FK_CUSTOMER_CODE as CUSTOMER_CODE,   
                        CUSTOMER_NAME, 
                        ADDRESS_LINE_1,
                        ADDRESS_LINE_2,
                        ADDRESS_LINE_3, 
                        ADDRESS_LINE_4, 
                        CITY,
                        STATE, 
                        DN_COUNTRY_CODE, 
                        ZIP,
                        '''' as NOTIFY2                        
                         
                FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE IN(''N'',''1'') 
                AND (FK_BL_NO) IN ('||P_I_V_BL||')';
            EXECUTE IMMEDIATE  V_SQL;
            COMMIT;
    
    END RCL_IGM_SAVE_UNFETCHED_NODIFY_PARTY_DATA;	

    
    PROCEDURE RCL_IGM_DELETE_NODIFY_PARTY_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_NODIFY_PARTY_DTLS   IN      CLOB
        ) 
    
            IS
            V_SQL varchar(32000);
            BEGIN
    
            V_SQL:='DELETE FROM IGM_NODIFY_PARTY_IGM_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
    
    END RCL_IGM_DELETE_NODIFY_PARTY_DATA;	    
    
    PROCEDURE RCL_IGM_MASTER_NODIFY_PARTY_TWO_DATA(
    P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
    P_I_V_BL            VARCHAR2 DEFAULT NULL,
    P_O_V_ERROR         OUT VARCHAR2)     
    
        IS 
        l_v_sql VARCHAR2(32000);               
        BEGIN   
    
    
                l_v_sql:=   'SELECT 
                            ''origninal''   type,
                            FK_BL_NO as FK_BL_NO,
                            FK_CUSTOMER_CODE CUSTOMER_CODE, 
                            CUSTOMER_NAME, 
                            ADDRESS_LINE_1,
                            ADDRESS_LINE_2,
                            ADDRESS_LINE_3, 
                            ADDRESS_LINE_4, 
                            CITY, STATE, 
                            DN_COUNTRY_CODE, 
                            ZIP,
                            '''' as NOTIFY2
                            FROM RCLTBLS.DEX_BL_CUSTOMERS WHERE CUSTOMER_TYPE IN(''N'',''2'') AND FK_BL_NO IN ('||P_I_V_BL||')';  -- 2
    
            OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_MASTER_NODIFY_PARTY_TWO_DATA;

    PROCEDURE RCL_IGM_GET_SAVE_NODIFY_PARTY_TWO_DATA(
                    P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
                    P_I_V_BL            VARCHAR2 DEFAULT NULL,
                    P_O_V_ERROR         OUT VARCHAR2)     IS 


                    l_v_sql VARCHAR2(32000);               

        BEGIN   
                l_v_sql:='  SELECT 
                ''origninal''   type,
                PK_BL_NO as FK_BL_NO,
                CUSTOMER_CODE ,
                CUSTOMER_NAME,
                ADDRESS_LINE_1,
                ADDRESS_LINE_2,
                ADDRESS_LINE_3,
                ADDRESS_LINE_4,
                CITY,
                STATE,
                DN_COUNTRY_CODE,
                ZIP
                 from IGM_NODIFY_PARTY_TWO   
                WHERE   PK_BL_NO IN ('||P_I_V_BL||')';

        OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_SAVE_NODIFY_PARTY_TWO_DATA;

    PROCEDURE RCL_IGM_SAVE_NODIFY_PARTY_TWO_DATA(    
                        P_I_V_BL	    VARCHAR2,
                        P_I_V_NODIFY_PARTY_DTLS   IN      CLOB
                        ) 

            IS
            V_SQL varchar(32000);
            BEGIN

            V_SQL:='DELETE FROM IGM_NODIFY_PARTY_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
            EXECUTE IMMEDIATE  V_SQL; 
            COMMIT; 
            DELETE FROM IGM_DATA_SET_JSON;

            INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_NODIFY_PARTY_DTLS); COMMIT;                      
            
            --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';

            INSERT INTO VASAPPS.IGM_NODIFY_PARTY_TWO 
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
            ZIP

            FROM  JSON_TABLE
            (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
            COLUMNS ( PK_BL_NO PATH '$.blNo'
            , CUSTOMER_CODE PATH '$.costumerCode'
            , CUSTOMER_NAME PATH '$.costumerName'
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

    END RCL_IGM_SAVE_NODIFY_PARTY_TWO_DATA;

    PROCEDURE RCL_IGM_GET_PREV_DECLARATION(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
        P_I_V_BL   VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR        OUT VARCHAR2
        ) 
        
        IS 
        l_v_sql VARCHAR2(32000);
        BEGIN   
    
    -- OPEN P_O_REFIGMTABFIND FOR 
               l_v_sql:=' SELECT DISTINCT SYBLNO as BL_NO, 
                        ''''															    PREVIOUS_DECLARATION,	
                        ''''															    MCIN,
                        ''''															    CSN_NO,
                        ''''                                  CSN_DATE,
                        ''''                                  SPLIT_INDICATOR,
                        ''''																  PCIN
                        FROM   IDP005 IDP5 
                        --INNER JOIN IDP010 IDP10                          ON IDP5.SYBLNO = IDP10.AYBLNO 
                        WHERE   SYBLNO IN ('||P_I_V_BL||')';
    
        OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    END RCL_IGM_GET_PREV_DECLARATION;	   

--GET from Save

    PROCEDURE RCL_IGM_GET_SAVE_PREV_DECLARATION(
        P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
        P_I_V_BL            VARCHAR2 DEFAULT NULL,
        P_O_V_ERROR         OUT VARCHAR2
        ) 
        IS 
            l_v_sql VARCHAR2(32000);               
        BEGIN 
        
              l_v_sql:='SELECT 
                        PK_BL_NO as BL_NO,
                        PREVIOUS_DECLARATION  as PREVIOUS_DECLARATION,
                        SPLIT_INDICATOR as SPLIT_INDICATOR,
                        CSN_NO as CSN_NO,
                        CSN_DATE as CSN_DATE,
                        MCIN as MCIN,
                        PCIN as PCIN
                        FROM IGM_BL_DETAILS_PREVIOUS_DECLARATION_TWO                    
                        WHERE   PK_BL_NO IN ('||P_I_V_BL||')';
                    
             OPEN P_O_REFIGMTABFIND FOR l_v_sql;
    
    END RCL_IGM_GET_SAVE_PREV_DECLARATION;   


--Insert into Save

    PROCEDURE RCL_IGM_SAVE_PREV_DECLARATION_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_PREV_DECLARATION_DTLS   IN      CLOB
        ) 
    
        IS
        V_SQL varchar(32000);
        BEGIN
    
    -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
        V_SQL:='DELETE FROM IGM_BL_DETAILS_PREVIOUS_DECLARATION_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
        EXECUTE IMMEDIATE  V_SQL; 
        COMMIT; 
        DELETE FROM IGM_DATA_SET_JSON;
        
        INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_PREV_DECLARATION_DTLS); COMMIT;                      
    
        --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';
    
            INSERT INTO IGM_BL_DETAILS_PREVIOUS_DECLARATION_TWO
            SELECT BL_NO_PK,
            PREVIOUS_DECLARATION,
            SPLIT_INDICATOR,
            CSN_NO,
            PCIN,
            CSN_DATE,
            MCIN
            FROM  JSON_TABLE(
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]' 
            COLUMNS ( BL_NO_PK PATH '$.blNo'
            ,PREVIOUS_DECLARATION PATH '$.previous_declaration'
            , SPLIT_INDICATOR PATH '$.split_indicator'
            , CSN_NO PATH '$.csn_number'
            , PCIN PATH '$.previous_pcin'
            , CSN_DATE PATH '$.csn_date'
            , MCIN PATH '$.previous_mcin')
            );COMMIT;
    
    END RCL_IGM_SAVE_PREV_DECLARATION_DATA;	  

     PROCEDURE RCL_IGM_SAVE_UNFETCHED_PREV_DECLARATION_DATA(   
        P_I_V_BL	    VARCHAR2 
        ) 
    
        IS
        V_SQL varchar2(32000);
        BEGIN
    
       insert into sidtest values ('p_i_v_bl'||P_I_V_BL);
            commit;
                
    -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);
    
        V_SQL:='DELETE FROM IGM_BL_DETAILS_PREVIOUS_DECLARATION_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
        EXECUTE IMMEDIATE  V_SQL; 
        COMMIT; 
         
  
          V_SQL:= 'INSERT INTO IGM_BL_DETAILS_PREVIOUS_DECLARATION_TWO
            (PK_BL_NO,
            PREVIOUS_DECLARATION,
            SPLIT_INDICATOR,
            CSN_NO,
            PCIN,
            CSN_DATE,
            MCIN)
            SELECT DISTINCT SYBLNO as PK_BL_NO, 
            ''''									PREVIOUS_DECLARATION,
            ''''                                  SPLIT_INDICATOR,
            ''''									CSN_NO,
            ''''									PCIN,
            ''''                                  CSN_DATE,
            ''''									MCIN
            FROM   IDP005 IDP5 
            WHERE SYBLNO IN ('||P_I_V_BL||')';  
            EXECUTE IMMEDIATE  V_SQL;
            COMMIT;
    
    END RCL_IGM_SAVE_UNFETCHED_PREV_DECLARATION_DATA;	  


    PROCEDURE RCL_IGM_DELETE_PREV_DECLARATION_DATA(   
        P_I_V_BL	    VARCHAR2,
        P_I_V_PREV_DECLARATION_DTLS   IN      CLOB
        ) 
        IS
            V_SQL varchar(32000);
        BEGIN
        
        V_SQL:='DELETE FROM IGM_BL_DETAILS_PREVIOUS_DECLARATION_TWO   WHERE PK_BL_NO in('|| P_I_V_BL||')'; 
        EXECUTE IMMEDIATE  V_SQL; 
        COMMIT; 
    
    END RCL_IGM_DELETE_PREV_DECLARATION_DATA;	
    
END RCL_IGM_BL_INFO_EXPORT;