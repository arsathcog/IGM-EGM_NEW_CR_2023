
  CREATE TABLE "VASAPPS"."DEX_BL_HEADER_TEMP" 
   (	"MBL_NO" VARCHAR2(20 CHAR), 
	"MA_SEQ_NO" NUMBER(20,0), 
	"DN_POD" VARCHAR2(20 CHAR), 
	"DN_POL" VARCHAR2(20 CHAR), 
	"MET_MT" NUMBER(20,0), 
	"MET_WT" NUMBER(20,0), 
	"DN_PLR" VARCHAR2(20 CHAR), 
	"POINT_NAME" VARCHAR2(35 CHAR), 
	"PK_BL_NO" VARCHAR2(20 CHAR), 
	"DN_PLD" VARCHAR2(20 CHAR), 
	"HBL_COUNT" VARCHAR2(20 CHAR), 
	 PRIMARY KEY ("MBL_NO")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "VASDATA"  ENABLE
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "VASDATA" ;

  CREATE INDEX "VASAPPS"."INDEX_DEX_BL_HEADER" ON "VASAPPS"."DEX_BL_HEADER_TEMP" ("MA_SEQ_NO") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "VASDATA" ;


  GRANT SELECT ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "VASAPPS_RO";
  GRANT DELETE ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "VASAPPS_RW";
  GRANT INSERT ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "VASAPPS_RW";
  GRANT SELECT ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "VASAPPS_RW";
  GRANT UPDATE ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "VASAPPS_RW";
  GRANT DELETE ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "EDWAPPS";
  GRANT INSERT ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "EDWAPPS";
  GRANT SELECT ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "EDWAPPS";
  GRANT UPDATE ON "VASAPPS"."DEX_BL_HEADER_TEMP" TO "EDWAPPS";