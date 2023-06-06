create or replace PACKAGE BODY RCL_IGM_FILE_INFO 
IS 
     PROCEDURE RCL_IGM_ACK_FILE (  
          P_O_REFIGMTABFIND  OUT  SYS_REFCURSOR, 
          P_I_V_BL            VARCHAR2 DEFAULT NULL,
          P_I_V_POD           VARCHAR2 DEFAULT NULL,
          P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL,
          P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,
          P_I_V_POD_TERMINAL   VARCHAR2 DEFAULT NULL,
          P_I_V_CONSIGNEE_COUNTRYCODE  VARCHAR2 DEFAULT NULL,
          P_I_V_CONSIGNEE_STATE  VARCHAR2 DEFAULT NULL,
          p_o_v_error        OUT  VARCHAR2 
          
    ) IS
        

--        v_sql VARCHAR2(30000);
--        TEMP_VAR VARCHAR2(1000);
--        PREPARE_JSON JSON_OBJECT_T;
--        PREPARE_JSON_CLOB CLOB;
       
    BEGIN
              P_O_V_ERROR := '000000'; 

        insert into sushiltest2 values ('p_i_v_bl'||P_I_V_BL);
        commit;
            insert into sushiltest2 values ('P_I_V_POD'||P_I_V_POD);
            commit;
                insert into sushiltest2 values ('P_I_V_POD_TERMINAL'||P_I_V_POD_TERMINAL);
                commit;
                    insert into sushiltest2 values ('P_I_V_VESSEL'||P_I_V_VESSEL);
                    commit;
                        insert into sushiltest2 values ('P_I_V_VOYAGE'||P_I_V_VOYAGE);
                        commit;
                        insert into sushiltest2 values ('P_I_V_CONSIGNEE_COUNTRYCODE'||P_I_V_CONSIGNEE_COUNTRYCODE);
                        commit;
                        insert into sushiltest2 values ('P_I_V_CONSIGNEE_STATE'||P_I_V_CONSIGNEE_STATE);
                        commit;
  
        
--        IF P_I_V_CONTAINER_NUM IS  NULL THEN
--        
--        BEGIN

     OPEN P_O_REFIGMTABFIND FOR   
             select RCLTBLS.DEX_BL_HEADER.FK_HOUSE_BL_NO,SEALINER.ITP185.GST_STATE_CODE,RCLTBLS.DEX_BL_HEADER.DN_PLR,
              (SELECT POINT_NAME  from rcltbls.cam_point WHERE PK_POINT_CODE IN
              (select  DN_PLR   from RCLTBLS.DEX_BL_HEADER where PK_BL_NO =P_I_V_BL)) AS RECIEPT_NAME,
             (SELECT POINT_NAME  from rcltbls.cam_point WHERE PK_POINT_CODE IN 
             (select  DN_PLD   from RCLTBLS.DEX_BL_HEADER where PK_BL_NO =P_I_V_BL)) AS ACCEPTANCE_NAME,
             rcltbls.cam_port.PORT_NAME,RCLTBLS.DEX_BL_HEADER.DN_PLD  from   
             RCLTBLS.DEX_BL_HEADER,SEALINER.ITP185,rcltbls.cam_port
             where 
             STCNCD = P_I_V_CONSIGNEE_COUNTRYCODE AND 
             ( P_I_V_CONSIGNEE_STATE IS NULL
                OR STCODE = P_I_V_CONSIGNEE_STATE ) 
            AND  PK_BL_NO = P_I_V_BL AND 
             PK_PORT_CODE =  P_I_V_POD
             AND ROWNUM = 1;
            insert into sushiltest2 values ('After execution'||P_I_V_BL);
            commit;

        END RCL_IGM_ACK_FILE;
        
        
         PROCEDURE RCL_IGM_STOWAGE_IMPORT(
          P_O_REFIGMTABFIND  OUT  SYS_REFCURSOR,
            P_I_V_BL              VARCHAR2 DEFAULT NULL,
           P_I_V_CONTAINER_NUM  VARCHAR2 DEFAULT NULL,
           P_I_V_POD           VARCHAR2 DEFAULT NULL,
           P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,
          P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL,
          P_I_V_POD_TERMINAL   VARCHAR2 DEFAULT NULL,
          p_o_v_error        OUT  VARCHAR2 
      )IS

        
        BEGIN

        P_O_V_ERROR:='000000';

OPEN p_o_refigmtabfind FOR SELECT
                               nvl(stowage_position, NULL) AS stowage_position
                           FROM
                               vasapps.tos_dl_booked_discharge
                           WHERE
                                   dn_equipment_no = p_i_v_container_num
                               AND EXISTS (
                                   SELECT
                                       FK_DISCHARGE_LIST_ID
                                   FROM
                                       vasapps.TOS_DL_BOOKED_DISCHARGE
                                   WHERE
                                       FK_DISCHARGE_LIST_ID IN (
                                           SELECT
                                               NVL(PK_DISCHARGE_LIST_ID, NULL) AS  FK_DISCHARGE_LIST_ID 
                                           FROM
                                               vasapps.TOS_DL_discharge_LIST
                                           WHERE
                                                   fk_vessel = p_i_v_vessel
                                               AND fk_voyage = p_i_v_voyage
--                                               AND dn_port = p_i_v_pod
                                               AND dn_terminal = P_I_V_POD_TERMINAL
                                       )
                               )
                               AND fk_booking_no IN (
                                   SELECT
                                       fk_booking_no
                                   FROM
                                       rcltbls.bl_booking_mapping
                                   WHERE
                                       fk_bl_no = p_i_v_bl
                               )
                           FETCH FIRST 1 ROW ONLY;
        
       END RCL_IGM_STOWAGE_IMPORT;
       
       
       
         PROCEDURE RCL_IGM_STOWAGE_EXPORT(
          P_O_REFIGMTABFIND  OUT  SYS_REFCURSOR,
            P_I_V_BL              VARCHAR2 DEFAULT NULL,
           P_I_V_CONTAINER_NUM  VARCHAR2 DEFAULT NULL,
           P_I_V_POL           VARCHAR2 DEFAULT NULL,
           P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,
          P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL,
          P_I_V_POL_TERMINAL   VARCHAR2 DEFAULT NULL,
          p_o_v_error        OUT  VARCHAR2 
      )IS

        
        BEGIN
        
        P_O_V_ERROR:='000000';
        
          insert into sushiltest2 values ('p_i_v_bl'||P_I_V_BL);
        commit;
            insert into sushiltest2 values ('P_I_V_POL'||P_I_V_POL);
            commit;
                insert into sushiltest2 values ('P_I_V_POL_TERMINAL'||P_I_V_POL_TERMINAL);
                commit;
                    insert into sushiltest2 values ('P_I_V_VESSEL'||P_I_V_VESSEL);
                    commit;
                        insert into sushiltest2 values ('P_I_V_VOYAGE'||P_I_V_VOYAGE);
                        commit;
                        insert into sushiltest2 values ('P_I_V_CONTAINER_NUM'||P_I_V_CONTAINER_NUM);
                        commit;
           
        OPEN P_O_REFIGMTABFIND FOR   
        
 SELECT
    nvl(stowage_position, 'NULL') AS stowage_position
FROM
    vasapps.TOS_LL_BOOKED_LOADING
WHERE
        dn_equipment_no = p_i_v_container_num
    AND EXISTS (
        SELECT
            FK_LOAD_LIST_ID
        FROM
           VASAPPS.TOS_LL_BOOKED_LOADING
        WHERE
           FK_LOAD_LIST_ID IN (
                SELECT
                    PK_LOAD_LIST_ID
                FROM
                    vasapps.TOS_LL_LOAD_LIST
                WHERE
                        fk_vessel = p_i_v_vessel
                    AND fk_voyage = p_i_v_voyage
                    AND dn_port = P_I_V_POL
                    AND dn_terminal = P_I_V_POL_TERMINAL
            )
    )
    AND fk_booking_no IN (
        SELECT
            fk_booking_no
        FROM
            rcltbls.bl_booking_mapping
        WHERE
            fk_bl_no = p_i_v_bl
    )
FETCH FIRST 1 ROW ONLY;

        
       END RCL_IGM_STOWAGE_EXPORT;
--    
      END RCL_IGM_FILE_INFO;