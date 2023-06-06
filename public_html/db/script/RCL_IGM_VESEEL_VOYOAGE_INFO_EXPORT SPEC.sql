create or replace PACKAGE   RCL_IGM_VESEEL_VOYOAGE_INFO_EXPORT AS


   --- To save data of VESEEL VOYOAGE tab
 PROCEDURE RCL_IGM_SAVE_VESEEL_VOYOAGE_SAVE_DATA(   
                        P_I_V_POL	                        VARCHAR2,
                        P_I_V_SERVICE	                    VARCHAR2,
                        P_I_V_VESSEL	                    VARCHAR2,
                        P_I_V_VOYAGE	                    VARCHAR2,
                        P_I_V_POL_TERMINAL	                VARCHAR2,
                        P_I_V_VESSEL_VOYOAGE_DTLS   IN      CLOB
        );
  
PROCEDURE rcl_igm_save_person_detailes ( 
                        P_I_V_BL	    VARCHAR2,
                        P_I_V_VESSEL    VARCHAR2 DEFAULT NULL,     
                        P_I_V_VOYAGE    VARCHAR2 DEFAULT NULL,
                        P_I_V_POD       VARCHAR2,
                        P_I_C_CONT_LST  IN      CLOB
);



PROCEDURE RCL_IGM_GET_DELETE_CSV_DATA(

                P_I_V_BL            VARCHAR2 DEFAULT NULL,
                P_I_V_VESSEL        VARCHAR2 DEFAULT NULL,
                P_I_V_VOYAGE        VARCHAR2 DEFAULT NULL,
                P_I_V_POD           VARCHAR2 DEFAULT NULL,
                P_I_V_CSV_Check     VARCHAR2 DEFAULT NULL

            );


PROCEDURE RCL_IGM_CREW_EFFECTS_FORMAT (  P_I_V_VESSEL    VARCHAR2 DEFAULT NULL,     
                        P_I_V_VOYAGE    VARCHAR2 DEFAULT NULL,
                        P_I_V_POD       VARCHAR2,
                        P_I_C_CONT_LST  IN      CLOB);

PROCEDURE RCL_IGM_SHIP_STORE_FORMAT (P_I_V_VESSEL    VARCHAR2 DEFAULT NULL,     
                        P_I_V_VOYAGE    VARCHAR2 DEFAULT NULL,
                        P_I_V_POD       VARCHAR2,
                        P_I_C_CONT_LST  IN      CLOB);   

 PROCEDURE RCL_IGM_GET_SAVED_VESEEL_VOYOGE_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
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
                             P_O_V_ERROR        OUT VARCHAR2) ;




 PROCEDURE RCL_IGM_VESSLE_VOYAGE_GET_MASTER_DATA(P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
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
                             P_O_V_ERROR        OUT VARCHAR2); 

PROCEDURE RCL_IGM_GET_SAVE_PERSON_ON_BOARD(
P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
--P_I_V_BL            VARCHAR2 DEFAULT NULL,
P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,     
P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
P_I_V_POD VARCHAR2 DEFAULT NULL,
P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_GET_SAVE_CREW_EFFECT(
P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
--P_I_V_BL            VARCHAR2 DEFAULT NULL,
P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,     
P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
P_I_V_POD VARCHAR2 DEFAULT NULL,
P_O_V_ERROR         OUT VARCHAR2
);

PROCEDURE RCL_IGM_GET_SAVE_SHIP_STORE(
P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                  
--P_I_V_BL            VARCHAR2 DEFAULT NULL,
P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,     
P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
P_I_V_POD VARCHAR2 DEFAULT NULL,
P_O_V_ERROR         OUT VARCHAR2
);                                         

PROCEDURE RCL_IGM_APPLY_IGM(

                P_I_V_BL            VARCHAR2 DEFAULT NULL,
                P_I_V_IGM_NUMBER      VARCHAR2 DEFAULT NULL,
              	P_I_V_IGM_DATE      VARCHAR2 DEFAULT NULL

            );  

END RCL_IGM_VESEEL_VOYOAGE_INFO_EXPORT ;