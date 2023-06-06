create or replace PACKAGE         RCL_IGM_EXPORT_INFO
IS
PROCEDURE 
            RCL_IGM_EXPORT_GET_DATA(   P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,    
                                P_I_V_POD		    VARCHAR2,
                                P_I_V_BL            VARCHAR2 DEFAULT NULL,
                                P_I_V_SERVICE       VARCHAR2 DEFAULT NULL,
                                P_I_V_VESSEL        VARCHAR2 DEFAULT NULL,
                                P_I_V_VOYAGE        VARCHAR2 DEFAULT NULL,
                                P_I_V_POD_TERMINAL  VARCHAR2 DEFAULT NULL,
                                P_I_V_FROM_DATE     VARCHAR2 DEFAULT NULL,
                                P_I_V_TO_DATE       VARCHAR2 DEFAULT NULL,
                                P_I_V_BL_STATUS     VARCHAR2 DEFAULT NULL,
                                P_I_V_POL           VARCHAR2 DEFAULT NULL,
                                P_I_V_DEL           VARCHAR2 DEFAULT NULL,
                                P_I_V_DEPOT         VARCHAR2 DEFAULT NULL,
                                P_I_V_POL_TERMINAL  VARCHAR2 DEFAULT NULL,
                                P_I_V_DIRECTION     VARCHAR2 DEFAULT NULL,
                                P_O_V_ERROR         OUT VARCHAR2
							);


END RCL_IGM_EXPORT_INFO ;