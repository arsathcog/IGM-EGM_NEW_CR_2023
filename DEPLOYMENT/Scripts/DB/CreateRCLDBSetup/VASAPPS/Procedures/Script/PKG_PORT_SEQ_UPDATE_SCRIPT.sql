DECLARE 
BEGIN
    PKG_PORT_SEQ_UPDATE.PRE_UPDATE_PORT_SEQ_EZLL;
EXCEPTION  
	WHEN OTHERS THEN NULL;
END;
/