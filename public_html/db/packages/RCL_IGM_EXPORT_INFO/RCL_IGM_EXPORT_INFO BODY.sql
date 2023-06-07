create or replace PACKAGE BODY RCL_IGM_EXPORT_INFO 
IS 
  PROCEDURE RCL_IGM_EXPORT_GET_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
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
                         ''                                  BL_STATUS, 
                         ''                                  BL_DATE,
                         SERVICE                             SERVICE, 
                         VESSEL                              VESSEL, 
                         VOYAGE                              VOYAGE, 
                         ACT_PORT_DIRECTION                  DIRECTION, 
                         LOAD_PORT                           POL, 
                         FROM_TERMINAL                       POL_TERMINAL,
                         IDP10.AYDEST                        DEL_VLS,
                         TO_TERMINAL                         DEPOT_VLS,
                         DISCHARGE_PORT                      POD, 
                         TO_TERMINAL                         POD_TERMINAL, 
                         TO_TERMINAL                         TERMINAL,
                         ''                                  BL_NO,
                         ''                                  CUSTOM_TERMINAL_CODE
        FROM   IDP005 IDP5 
                         INNER JOIN IDP010 IDP10 
                                 ON IDP5.SYBLNO = IDP10.AYBLNO 
                  WHERE  
                       --  IDP5.DISCHARGE_PORT = P_I_V_POD   AND
                ( P_I_V_SERVICE IS NULL 
                        OR IDP5.SERVICE = P_I_V_SERVICE ) 
                 AND ( P_I_V_VESSEL IS NULL 
                        OR IDP5.VESSEL = P_I_V_VESSEL ) 
                 AND ( P_I_V_VOYAGE IS NULL 
                        OR IDP5.VOYAGE  = P_I_V_VOYAGE ) 
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




      END RCL_IGM_EXPORT_GET_DATA; 



	  END RCL_IGM_EXPORT_INFO;