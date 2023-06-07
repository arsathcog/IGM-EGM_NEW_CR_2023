create or replace PACKAGE BODY RCL_IGM_VESEEL_VOYOAGE_INFO_EXPORT AS

 

 PROCEDURE RCL_IGM_SAVE_VESEEL_VOYOAGE_SAVE_DATA(   
                        P_I_V_POL	                        VARCHAR2,
                        P_I_V_SERVICE	                    VARCHAR2,
                        P_I_V_VESSEL	                    VARCHAR2,
                        P_I_V_VOYAGE	                    VARCHAR2,
                        P_I_V_POL_TERMINAL	                VARCHAR2,
                        P_I_V_VESSEL_VOYOAGE_DTLS   IN      CLOB 
        ) 

       AS
      V_SQL varchar(1000);
        BEGIN

    V_SQL:='DELETE FROM IGM_VESSEL_VOYAGE_DETAILES   WHERE SERVICE in('||''''|| P_I_V_SERVICE||''''||') and POL in('||''''|| P_I_V_POL||''''||') and POL_TERMINAL in('||''''|| P_I_V_POL_TERMINAL||''''||') and VESSEL in('||''''|| P_I_V_VESSEL||''''||') and VOYAGE in('||''''|| P_I_V_VOYAGE||''''||')';     

      EXECUTE IMMEDIATE  V_SQL; COMMIT;    


     DELETE FROM IGM_DATA_SET_JSON;   

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_V_VESSEL_VOYOAGE_DTLS); COMMIT;                        

     --UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';


      INSERT INTO IGM_VESSEL_VOYAGE_DETAILES
     SELECT          
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
                    TOTAL_NO_OF_TRANSPORT_EQUIPMENT_REPORTED_ON_ARRIVAL_DEPARTURE,
                    VASSELCODE	     ,
                    EDI	            ,
                    NON_EDI	 ,
                    PARENT_VOY	 ,
                    VIA_VCN	 ,
                    SUB_TERMIL	 ,
                    TYPE_TRANSPORT_MEANS	 ,
                    EQUIMENT_TYPE	 ,
                    IGM_YEAR	 ,
                    ROTN_NO	 ,
                    ROTN_DATE	 ,
                    JOB_NO	 ,
                    JOB_DATE	 ,
                    POSITION	 ,
                    EXCHANGE_RATE	 ,
                    CIGM_NO	 ,
                    CIGM_DATE	 ,
                    SMTP_NO	 ,
                    SMTP_DATE	 ,
                    NO_OF_ITEM_IN_PRIOR	 ,
                    NO_OF_ITEM_IN_FIL	 ,
                    NO_OF_ITEM_IN_SUPPLIMENTARY	 ,
                    TOTAL_WEIGHT	 ,
                    NO_OF_PASSENGER	 ,
                    NO_OF_CREW	 ,
                    REMARK_VESSEL,
                    VESSEL_NEW  , 
                    VOYAGE_NEW ,
                    TERMINAL_PORT,
                    light_due,
                    SENDER_ID,
                    RECIEVER_ID,
                    AUTHREPRSNTVCD,
                    LAST_PORT_1_NAME,
                    LAST_PORT_2_NAME,
                    LAST_PORT_3_NAME,
                    NEXT_PORT_4_NAME,
                    NEXT_PORT_5_NAME,
                    NEXT_PORT_6_NAME,
                    FLAG_DISCHARGE
     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS (    
                            SERVICE PATH '$.service',
							POD PATH '$.pod',
							POD_TERMINAL PATH '$.podTerminal',
							VESSEL PATH '$.vessel',
							VOYAGE PATH '$.voyage',
							CUST_CODE PATH '$.customCode',
							CALL_SIGN PATH '$.callSing',
							LINE_CODE PATH '$.lineCode',
							AGENT_CODE PATH '$.agentCode',
							PORT_ORIGIN PATH '$.portOrigin',
							PORT_ARRIVAL PATH '$.portArrival',
							LAST_PORT_1 PATH '$.lastPort1',
							LAST_PORT_2 PATH '$.lastPort2',
							LAST_PORT_3 PATH '$.lastPort3',
							NEXT_PORT_4 PATH '$.nextport1',
							NEXT_PORT_5 PATH '$.nextport2',
							NEXT_PORT_6 PATH '$.nextport3',
							TERMINAL PATH '$.terminal',
							VESSEL_TYPE PATH '$.vesselType',
							GEN_DESC PATH '$.genDesc',
							MASTER_NAME PATH '$.masterName',
							VESSEL_NATION PATH '$.vesselNation',
							IGM_NUMBER PATH '$.igmNumber',
							IGM_DATE PATH '$.igmDate',
							ARRIVAL_DATE PATH '$.arrivalDate',
							ARRIVAL_TIME PATH '$.arrivalTime',
							ARRIVAL_DATE_ATA PATH '$.ataarrivalDate',
							ARRIVAL_TIME_ATA PATH '$.ataarrivalTime',
							TOTAL_BLS PATH '$.totalItem',
							SM_BT_CARGO PATH '$.smBtCargo',
							SHIP_STR_DECL PATH '$.shipStrDect',
							CREW_EFFECT PATH '$.crewEffect',
							MARITIME_DECL PATH '$.mariTimeDecl',
							ITEM_NUMBER PATH '$.itemNumber',
							CARGO_NATURE PATH '$.cargoNature',
							CARGO_MOVMNT PATH '$.cargoMovmnt',
							ITEM_TYPE PATH '$.itemType',
							CARGO_MOVMNT_TYPE PATH '$.cargoMovmntType',
							TRANSPORT_MODE PATH '$.transportMode',
							ROAD_CARR_CODE PATH '$.roadCarrCode',
							ROAD_TP_BOND_NO PATH '$.roadTPBondNo',
							NEW_VOYAGE PATH '$.newVoyage',
							NEW_VESSEL PATH '$.newVessel',
							SUBMIT_DATE_TIME PATH '$.submitDateTime',
							NHAVA_SHEVA_ETA PATH '$.nhavaShevaEta',
							FINAL_PLACE_DELIVERY PATH '$.finalPlaceDelivery',
							PACKAGES PATH '$.packages',
							CFS_NAME PATH '$.cfsName',
							MBL_NO PATH '$.mblNo',
							HBL_NO PATH '$.hblNo',
							FROM_ITEM_NO PATH '$.fromItemNo',
							TO_ITEM_NO PATH '$.toItemNo',
							NET_WEIGHT PATH '$.netWeight',
							GROSS_WEIGHT PATH '$.grossWeight',
							IMO_CODE PATH '$.imoCode',
							BL_DATE PATH '$.blDate',
							BL_STATUS PATH '$.blStatus',
							POL PATH '$.pol',
							CUSTOM_TERMINAL_CODE PATH '$.customTerminalCode',
							BL_VERSION PATH '$.blVersion',
							DPD_CODE PATH '$.dpdCode',
							DPD_MOVEMENT PATH '$.dpdMovement',
							POL_TERMINAL PATH '$.polTerminal',
							CARGO_DECL PATH '$.cargoDeclaration',
							CREW_LST_DECL PATH '$.crewListDeclaration',
							PASSNGR_LIST PATH '$.passengerList',
							CUSTOMERS_ADDRESS_1 PATH '$.CusAdd1',
							CUSTOMERS_ADDRESS_2 PATH '$.CusAdd2',
							CUSTOMERS_ADDRESS_3 PATH '$.CusAdd3',
							CUSTOMERS_ADDRESS_4 PATH '$.CusAdd4',
							COLOR_FLAG PATH '$.IsValidateBL',
							NET_WEIGHT_METRIC PATH '$.netWeight',
							NET_PACKAGE PATH '$.PackageBLLevel',
							DEL_VLS PATH '$.del',
							DEPOT_VLS PATH '$.depot',
							DEP_MANIF_NO PATH '$.dep_manif_no',
							DEP_MANIFEST_DATE PATH '$.dep_manifest_date',
							SUBMITTER_TYPE PATH '$.submitter_type',
							SUBMITTER_CODE PATH '$.submitter_code',
							AUTHORIZ_REP_CODE PATH '$.authoriz_rep_code',
							SHIPPING_LINE_BOND_NO_R PATH '$.shipping_line_bond_no_r',
							MODE_OF_TRANSPORT PATH '$.mode_of_transport',
							SHIP_TYPE PATH '$.ship_type',
							CONVEYANCE_REFERENCE_NO PATH '$.conveyance_reference_no',
							CARGO_DESCRIPTION PATH '$.cargo_description',
							TOL_NO_OF_TRANS_EQU_MANIF PATH '$.tol_no_of_trans_equ_manif',
							BRIEF_CARGO_DES PATH '$.brief_cargo_des',
							EXPECTED_DATE PATH '$.expected_date',
							TIME_OF_DEPT PATH '$.time_of_dept',
							TOTAL_NO_OF_TRAN_S_CONT_REPO_ON_ARI_DEP PATH '$.total_no_of_tran_s_cont_repo_on_ari_dep',
							MESSAGE_TYPE PATH '$.message_type',
							VESSEL_TYPE_MOVEMENT PATH '$.vessel_type_movement',
							AUTHORIZED_SEA_CARRIER_CODE PATH '$.authorized_sea_carrier_code',
							PORT_OF_REGISTRY PATH '$.port_of_registry',
							REGISTRY_DATE PATH '$.registry_date',
							VOYAGE_DETAILS PATH '$.voyage_details_movement',
							SHIP_ITINERARY_SEQUENCE PATH '$.ship_itinerary_sequence',
							SHIP_ITINERARY PATH '$.ship_itinerary',
							PORT_OF_CALL_NAME PATH '$.port_of_call_name',
							ARRIVAL_DEPARTURE_DETAILS PATH '$.arrival_departure_details',
							TOTAL_NO_OF_TRANSPORT_EQUIPMENT_REPORTED_ON_ARRIVAL_DEPARTURE PATH '$.total_no_of_transport_equipment_reported_on_arrival_departure',
                            VASSELCODE PATH '$.vasselCode',
							EDI PATH '$.edi',
							NON_EDI PATH '$.nonEdi',
							PARENT_VOY PATH '$.parentVoy',
							VIA_VCN PATH '$.viaVcn',
							SUB_TERMIL PATH '$.subTermil',
							TYPE_TRANSPORT_MEANS PATH '$.typeTransportMeans',
							EQUIMENT_TYPE PATH '$.equimentType',
							IGM_YEAR PATH '$.igmYear',
							ROTN_NO PATH '$.rotnNo',
							ROTN_DATE PATH '$.rotnDate',
							JOB_NO PATH '$.jobNo',
							JOB_DATE PATH '$.jobDate',
							POSITION PATH '$.position',
							EXCHANGE_RATE PATH '$.exchangeRate',
							CIGM_NO PATH '$.cigmNo',
							CIGM_DATE PATH '$.cigmDate',
							SMTP_NO PATH '$.smtpNo',
							SMTP_DATE PATH '$.smtpDate',
							NO_OF_ITEM_IN_PRIOR PATH '$.noOfItemInPrior',
							NO_OF_ITEM_IN_FIL PATH '$.noOfItemInFil',
							NO_OF_ITEM_IN_SUPPLIMENTARY PATH '$.noOfItemInSupplimentary',
							TOTAL_WEIGHT PATH '$.totalWeight',
							NO_OF_PASSENGER PATH '$.noOfPassenger',
							NO_OF_CREW PATH '$.noOfCrew',
							REMARK_VESSEL PATH '$.remark',
                            VESSEL_NEW PATH '$.newVessel',  
							VOYAGE_NEW PATH '$.newVoyage',
                            TERMINAL_PORT PATH '$.polTerminalPort',
                            light_due PATH '$.lightDue',
                            SENDER_ID PATH '$.senderId',
                            RECIEVER_ID PATH '$.recieverId',
                            AUTHREPRSNTVCD PATH '$.authReprsntvCd',
                            LAST_PORT_1_NAME PATH '$.port_of_call_name_last1', 
							LAST_PORT_2_NAME PATH '$.port_of_call_name_last2',
							LAST_PORT_3_NAME PATH '$.port_of_call_name_last3',
							NEXT_PORT_4_NAME PATH '$.port_of_call_name_nextport1',
							NEXT_PORT_5_NAME PATH '$.port_of_call_name_nextport2',
							NEXT_PORT_6_NAME PATH '$.port_of_call_name_nextport3',
                            FLAG_DISCHARGE PATH '$.flag_discharge'
                            

                        )
           );                   
      COMMIT;
 END RCL_IGM_SAVE_VESEEL_VOYOAGE_SAVE_DATA;

 PROCEDURE RCL_IGM_GET_SAVED_VESEEL_VOYOGE_DATA(
                             P_O_REFIGMTABFIND  OUT SYS_REFCURSOR, 
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

   AS
      V_SQL varchar(1000);

  BEGIN   




 OPEN P_O_REFIGMTABFIND FOR 
                SELECT
                        ''          BL_STATUS,
                        ''          BL_DATE,
                        SERVICE,
                        VESSEL,
                        (select VESSEL_NAME from RCLTBLS.CAM_VESSEL where PK_VESSEL=VESSEL) as VESSEL_NAME,
                        VOYAGE,
                        POL,
                        POL_TERMINAL,
                        DEL_VLS,
                        DEPOT_VLS,
                        POD,
                        POD_TERMINAL,
                        TERMINAL,
                        ''                      BL_NO,
                     	CUST_CODE                   , 
                        CALL_SIGN                   , 
                        LINE_CODE                   , 
                        AGENT_CODE                  , 
                        PORT_ORIGIN                 , 
                        PORT_ARRIVAL                , 
                        LAST_PORT_1                 ,  
                        LAST_PORT_2                 , 
                        LAST_PORT_3                 , 
                        NEXT_PORT_4                 , 
                        NEXT_PORT_5                 , 
                        NEXT_PORT_6                 , 
                        TERMINAL                           , 
                        VESSEL_TYPE                        , 
                        GEN_DESC                           , 
                        MASTER_NAME                        , 
                        VESSEL_NATION                      , 
                        IGM_NUMBER                         , 
                        IGM_DATE                           , 
                        ARRIVAL_DATE                       , 
                        ARRIVAL_TIME                       , 
                        ARRIVAL_DATE_ATA                   , 
                        ARRIVAL_TIME_ATA                   , 
                        TOTAL_BLS                          , 
                        SM_BT_CARGO                        ,
                        SHIP_STR_DECL                  ,
                        CREW_EFFECT                    ,
                        MARITIME_DECL                  ,
                        ITEM_NUMBER                     , 
                        CARGO_NATURE                   ,
                        CARGO_MOVMNT                  ,
                        ITEM_TYPE                     ,
                        CARGO_MOVMNT_TYPE               , 
                        TRANSPORT_MODE                 ,
                        ROAD_CARR_CODE                  , 
                        ROAD_TP_BOND_NO                 , 
                        NEW_VOYAGE                      , 
                        NEW_VESSEL                      , 
                        SUBMIT_DATE_TIME                , 
                        NHAVA_SHEVA_ETA                 , 
                        FINAL_PLACE_DELIVERY            , 
                        PACKAGES                        , 
                        CFS_NAME                        , 
                        MBL_NO                          , 
                        HBL_NO                          , 
                        FROM_ITEM_NO                    , 
                        TO_ITEM_NO                      , 
                        NET_WEIGHT                      , 
                        GROSS_WEIGHT                    ,
                        IMO_CODE                        , 
                        BL_DATE                         , 
                        BL_STATUS                      ,
                        POL                             , 
                        CUSTOM_TERMINAL_CODE            , 
                        BL_VERSION                      , 
                        DPD_CODE                        , 
                        DPD_MOVEMENT                    , 
                        POL_TERMINAL                    , 
                        CARGO_DECL                      , 
                        CREW_LST_DECL                  ,
                        PASSNGR_LIST                  ,
                        CUSTOMERS_ADDRESS_1            ,
                        CUSTOMERS_ADDRESS_2            ,
                        CUSTOMERS_ADDRESS_3            ,
                        CUSTOMERS_ADDRESS_4            ,
                        COLOR_FLAG                      , 
                        NET_WEIGHT_METRIC             ,
                        NET_PACKAGE                     , 
                        DEL_VLS                         , 
                        DEPOT_VLS                       , 
                        DEP_MANIF_NO                   ,
                        DEP_MANIFEST_DATE             ,
                        SUBMITTER_TYPE                ,
                        SUBMITTER_CODE                ,
                        AUTHORIZ_REP_CODE               , 
                        SHIPPING_LINE_BOND_NO_R       ,
                        MODE_OF_TRANSPORT            ,
                        SHIP_TYPE                      ,
                        CONVEYANCE_REFERENCE_NO       ,
                        CARGO_DESCRIPTION             ,
                        TOL_NO_OF_TRANS_EQU_MANIF      , 
                        BRIEF_CARGO_DES                , 
                        EXPECTED_DATE                  , 
                        TIME_OF_DEPT                   , 
                        TOTAL_NO_OF_TRAN_S_CONT_REPO_ON_ARI_DEP                           , 
                        MESSAGE_TYPE                  ,
                        VESSEL_TYPE_MOVEMENT           , 
                        AUTHORIZED_SEA_CARRIER_CODE    , 
                        PORT_OF_REGISTRY               , 
                        REGISTRY_DATE                  , 
                        VOYAGE_DETAILS                 , 
                        SHIP_ITINERARY_SEQUENCE        , 
                        SHIP_ITINERARY                 , 
                        PORT_OF_CALL_NAME              , 
                        ARRIVAL_DEPARTURE_DETAILS      ,
                        TOTAL_NO_OF_TRANSPORT_EQUIPMENT_REPORTED_ON_ARRIVAL_DEPARTURE                           , 
                        VASSELCODE                    , 
                        EDI                           , 
                        NON_EDI                       , 
                        PARENT_VOY                    , 
                        VIA_VCN                       , 
                        SUB_TERMIL                    , 
                        TYPE_TRANSPORT_MEANS          , 
                        EQUIMENT_TYPE                 , 
                        IGM_YEAR                      , 
                        ROTN_NO                       , 
                        ROTN_DATE                     , 
                        JOB_NO                        , 
                        JOB_DATE                      , 
                        POSITION                      , 
                        EXCHANGE_RATE                 , 
                        CIGM_NO                       ,  
                        CIGM_DATE                     , 
                        SMTP_NO                       , 
                        SMTP_DATE                     , 
                        NO_OF_ITEM_IN_PRIOR           , 
                        NO_OF_ITEM_IN_FIL             , 
                        NO_OF_ITEM_IN_SUPPLIMENTARY   , 
                        TOTAL_WEIGHT                  ,   
                        NO_OF_PASSENGER               , 
                        NO_OF_CREW                    , 
                        REMARK_VESSEL,
                        ''  LIGHT_DUE,
                        CUST_CODE        as CUSTOM_CODE,
                        ''               as   POD_TERMINAL_PORT,
                        TERMINAL_PORT    as   POL_TERMINAL_PORT,
                        SENDER_ID,
                        RECIEVER_ID,   
                        AUTHREPRSNTVCD,
                        LAST_PORT_1_NAME,
                        LAST_PORT_2_NAME,
                        LAST_PORT_3_NAME,
                        NEXT_PORT_4_NAME,
                        NEXT_PORT_5_NAME, 
                        NEXT_PORT_6_NAME,
                        (SELECT COUNT(BL_NO_PK) FROM IGM_PERSON_ON_BOARD WHERE  
                        VESSEL = P_I_V_VESSEL  AND  VOYAGE =  P_I_V_VOYAGE    AND POD = P_I_V_POL)                              noOfCrew 
                FROM
                    IGM_VESSEL_VOYAGE_DETAILES
                WHERE
                    ( P_I_V_SERVICE IS NULL
                      OR SERVICE = P_I_V_SERVICE )
                    AND ( P_I_V_VESSEL IS NULL
                          OR VESSEL = P_I_V_VESSEL )
                    AND ( P_I_V_VOYAGE IS NULL
                          OR VOYAGE = P_I_V_VOYAGE )
                    AND ( P_I_V_POD_TERMINAL IS NULL
                          OR POD = P_I_V_POD_TERMINAL )
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

	  P_O_V_ERROR:='000000';  			
END RCL_IGM_GET_SAVED_VESEEL_VOYOGE_DATA;

PROCEDURE RCL_IGM_SAVE_PERSON_DETAILES(   
                        P_I_V_BL	    VARCHAR2,
                        P_I_V_VESSEL    VARCHAR2 DEFAULT NULL,     
                        P_I_V_VOYAGE    VARCHAR2 DEFAULT NULL,
                        P_I_V_POD       VARCHAR2,
                        P_I_C_CONT_LST  IN      CLOB
        ) 

        IS
      V_SQL varchar(1000);
        BEGIN


      V_SQL:='DELETE FROM igm_person_on_board   WHERE vessel = ('''|| P_I_V_VESSEL ||''') 
              AND  VOYAGE  = ('''|| P_I_V_VOYAGE ||''')  
              AND  POD =  ('''|| P_I_V_POD ||''')   '; 

      EXECUTE IMMEDIATE  V_SQL; 
     COMMIT; 
     DELETE FROM IGM_DATA_SET_JSON;

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_C_CONT_LST); COMMIT;                      

     UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';




    INSERT INTO igm_person_on_board 
     SELECT  bl_no_pk                                                        ,
			type_code                                             ,
			family_name                                           ,
			given_name                                           ,
			nationality_code                                       ,
			intransit_indicator                                  ,
			crew_member_rankor_rating                             ,
			crew_member_rankorrating_code                         ,
			portofembarkationcode                                  ,
			port_of_embarkation_name                              ,
			port_of_disembarkation_code                           ,
			port_of_disembarkation_name                          ,
			gender_code                                            ,
			date_of_birth                                           ,
			place_of_birthname                                    ,
			country_of_birth_code                                 ,
			identity_doc_expiry_date                                       ,
			identity_or_travel_doc_issuing_nation_code            ,
			identity_or_travel_doc_nmbr                           ,
			identity_or_travel_doc_type_code                      ,
			visa                                                   ,
			pnr_number                                            ,
            vessel                                                ,
			voyage                                                ,
			pod                                                   
     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( 



						    bl_no_pk                                                PATH '$.blNo',
							type_code                                            PATH '$.prsnTypCdd',
							family_name                                           PATH '$.prsnFamilyName',
							given_name                                          PATH '$.prsnGivenName',
							nationality_code                                      PATH '$.prsnNatnltyCdd',
							intransit_indicator                                  PATH '$.psngrInTransitIndctr',
							crew_member_rankor_rating                            PATH '$.crewmemberRankOrRating',     
							crew_member_rankorrating_code                        PATH '$.crewmemberRankOrRatingCdd',
							portofembarkationcode                                PATH '$.psngrPrtOfEmbrktnCdd',
							port_of_embarkation_name                             PATH '$.psngrPrtOfEmbrktnName',
							port_of_disembarkation_code                          PATH '$.psngrPrtOfDsmbrktnCdd',
							port_of_disembarkation_name                          PATH '$.psngrPrtOfDsmbrktnName',
							gender_code                                          PATH '$.prsnGenderCdd',
							date_of_birth                                        PATH '$.prsnDtOfBirth',
							place_of_birthname                                   PATH '$.prsnPlaceOfBirthName',
							country_of_birth_code                                PATH '$.prsnCntryOfBirthCdd',
							identity_doc_expiry_date                             PATH '$.prsnIdDocExpiryDt',
							identity_or_travel_doc_issuing_nation_code           PATH '$.prsnIdOrTravelDocIssuingNationCdd',
							identity_or_travel_doc_nmbr                          PATH '$.prsnIdOrTravelDocNmbr',
							identity_or_travel_doc_type_code                     PATH '$.prsnIdOrTravelDocTypCdd',
							visa                                                  PATH '$.visa',
							pnr_number                                           PATH '$.pnrNumber',
                            vessel                                               PATH '$.vessel',
		                   	voyage                                               PATH '$.voyage',
			                pod                                                  PATH '$.pod'                         






                        )
           );
      COMMIT;

        END RCL_IGM_SAVE_PERSON_DETAILES;

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
                             P_O_V_ERROR    OUT VARCHAR2) 
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
                        TEMP_POL_TERMINAL VARCHAR2(20);
						V_SQL   VARCHAR2(6000); 
						V_SQL_CNDTN  VARCHAR2(4000); 
  BEGIN   

      TEMP_POL_TERMINAL:= P_I_V_POL_TERMINAL;

    

    FOR TEMP_POD_COUNT_PRIVIOUS in 1 .. 3 LOOP

      IF(TEMP_POD_COUNT_PRIVIOUS=1) THEN

        BEGIN


         
            SELECT VVPCAL,VVTRM1 INTO TEMP_LAST_PORT_3,TEMP_LAST_PORT_TER_3 FROM (
            SELECT VVPCAL,VVTRM1  FROM SEALINER.ITP063 WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99 AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))<=(
            SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000') AS ETADATE FROM SEALINER.ITP063 WHERE
            VVSRVC=P_I_V_SERVICE AND
            VVPCAL=P_I_V_POL AND
            VVVESS=P_I_V_VESSEL AND
            VVVOYN=P_I_V_VOYAGE AND
            VVTRM1=TEMP_POL_TERMINAL AND 
           -- VVPCSQ=PORTSEQUENCE' AND
            VVVERS=99 AND
            OMMISSION_FLAG IS NULL AND
            VVFORL IS NOT NULL )
            AND OMMISSION_FLAG IS NULL
            AND VVFORL IS NOT NULL
            AND (VVPCAL ,VVTRM1) NOT IN
            (SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
            VVSRVC= P_I_V_SERVICE AND
            VVPCAL=P_I_V_POL AND
            VVVESS=P_I_V_VESSEL AND
            VVVOYN=P_I_V_VOYAGE AND
            VVTRM1=TEMP_POL_TERMINAL AND 
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
                             
            SELECT VVPCAL,VVTRM1 into TEMP_NEXT_PORT_1,TEMP_LAST_PORT_TER_1 FROM (
			SELECT VVPCAL,VVTRM1 FROM SEALINER.ITP063 WHERE VVVESS = P_I_V_VESSEL AND VVVERS= 99 AND(VVARDT||NVL(LPAD(VVARTM,4,0),'0000'))>=(
			SELECT VVARDT||NVL(LPAD(VVARTM,4,0),'0000') AS ETADATE FROM SEALINER.ITP063 WHERE
			VVSRVC=P_I_V_SERVICE AND
			VVPCAL=P_I_V_POL AND
			VVVESS=P_I_V_VESSEL AND
			VVVOYN=P_I_V_VOYAGE AND
			VVTRM1=TEMP_POL_TERMINAL AND
			--VVPCSQ=PORTSEQUENCE' AND
			VVVERS=99 AND
			OMMISSION_FLAG IS NULL AND
			VVFORL IS NOT NULL )
			AND OMMISSION_FLAG IS NULL
			AND VVFORL IS NOT NULL
			AND (VVPCAL ,VVTRM1) NOT IN
			( SELECT VVPCAL ,VVTRM1 FROM SEALINER.ITP063 WHERE
			VVSRVC =P_I_V_SERVICE AND
			VVPCAL =P_I_V_POL AND
			VVVESS =P_I_V_VESSEL AND
			VVVOYN =P_I_V_VOYAGE AND
			VVTRM1=TEMP_POL_TERMINAL AND
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


  


       P_O_V_ERROR:='000000';      

         OPEN P_O_REFIGMTABFIND FOR  
         SELECT  ''                              BL_NO, 
                 ''                        BL_STATUS, 
                 ''                        BL_DATE, 
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
                 (SELECT PARTNER_VALUE
                  FROM EDI_TRANSLATION_DETAIL 
                  WHERE ETH_UID IN (
                  SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                  WHERE CODE_SET='IGMTML'
                  ) AND SEALINER_VALUE=TEMP_POL_TERMINAL AND ROWNUM=1)                     POL_TERMINAL_PORT,
                  ''                                                                    POD_TERMINAL_PORT,
                 (select VESSEL_NAME from RCLTBLS.CAM_VESSEL where PK_VESSEL=P_I_V_VESSEL) VESSEL_NAME,
                 (SELECT PARTNER_VALUE
                  FROM EDI_TRANSLATION_DETAIL 
                  WHERE ETH_UID IN (
                  SELECT ETH_UID FROM EDI_TRANSLATION_HEADER 
                  WHERE CODE_SET='IGMPORT'
                  ) AND SEALINER_VALUE=P_I_V_POL AND ROWNUM=1) CUSTOM_CODE,
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
                                              AND ETH.CODE_SET = 'AGENT_CODE') 
                         AND A.SEALINER_VALUE =P_I_V_POL)                                        LINE_CODE,           --NEED TO PUT
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
                                     VVPCAL = P_I_V_POL
                                            AND (P_I_V_SERVICE IS NULL
                                                    or VVSRVC =P_I_V_SERVICE)
                                     AND VVVESS = P_I_V_VESSEL 
                                     AND VVVOYN = P_I_V_VOYAGE 
                                     AND ( 
                                         P_I_V_POL_TERMINAL IS 
                                         NULL 
                                          OR VVTRM1 = P_I_V_POL_TERMINAL ) 
                                                 AND VVVERS = 99 
                                                 AND OMMISSION_FLAG IS NULL 
                                                 AND VVFORL IS NOT NULL) 
                                 AND OMMISSION_FLAG IS NULL 
                                 AND VVFORL IS NOT NULL 
                                 AND ( VVPCAL, VVTRM1 ) NOT IN 
                                     (SELECT VVPCAL, 
                                             VVTRM1 
                                      FROM   SEALINER.ITP063 
                                      WHERE  VVPCAL = P_I_V_POL
                                             AND (P_I_V_SERVICE IS NULL
                                                     or VVSRVC =P_I_V_SERVICE)
                                             AND VVVESS = P_I_V_VESSEL 
                                             AND VVVOYN = P_I_V_VOYAGE 
                                             AND ( 
                                     P_I_V_POL_TERMINAL IS NULL 
                                      OR VVTRM1 = P_I_V_POL_TERMINAL ) 
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
                         AND A.SEALINER_VALUE =P_I_V_POL)                                      AGENT_CODE,
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
                 ''                                  FROM_ITEM_NO,
                 ''                                  TO_ITEM_NO,
                  (SELECT VVAPDT 
                  FROM   ITP063 A 
                  WHERE  VVPCAL = P_I_V_POL
                         AND (P_I_V_SERVICE IS NULL
                                or VVSRVC =P_I_V_SERVICE)
                         AND VVVESS = P_I_V_VESSEL 
                         AND VVVOYN = P_I_V_VOYAGE 
                         AND ( P_I_V_POL_TERMINAL IS NULL 
                                OR VVTRM1 = P_I_V_POL_TERMINAL ) 
                         AND A.VVVERS = 99 
                         AND A.VVFORL IS NOT NULL 
                         AND A.OMMISSION_FLAG IS NULL 
                         AND ROWNUM < 2)                                  ARRIVAL_DATE, 
                   (SELECT VVAPTM 
                  FROM   ITP063 A 
                  WHERE  VVPCAL = P_I_V_POL 
                         AND (P_I_V_SERVICE IS NULL
                              or VVSRVC =P_I_V_SERVICE)
                         AND VVVESS = P_I_V_VESSEL 
                         AND VVVOYN = P_I_V_VOYAGE 
                         AND ( P_I_V_POL_TERMINAL IS NULL 
                                OR VVTRM1 = P_I_V_POL_TERMINAL ) 
                         AND A.VVVERS = 99 
                         AND A.VVFORL IS NOT NULL 
                         AND A.OMMISSION_FLAG IS NULL 
                         AND ROWNUM < 2)                                ARRIVAL_TIME,
                  ''                             ARRIVAL_DATE_ATA,
                  ''                             ARRIVAL_TIME_ATA,
                  --NEW MANIFENT FILE CR 14/111/2019
                  ''                        DEP_MANIF_NO,
                  ''                        DEP_MANIFEST_DATE,
                  'ASA'                        SUBMITTER_TYPE,
                  
                   (SELECT PARTNER_VALUE
                    FROM EDI_TRANSLATION_DETAIL 
                    
                    WHERE ETH_UID IN (
                    
                    SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                    WHERE CODE_SET='IGMSUBMITC'
                    
                    ) AND SEALINER_VALUE=(select PIOFFC from itp040 WHERE PICODE=P_I_V_POL)  -- fsc 
                    AND ROWNUM=1 AND RECORD_STATUS='A')                     SUBMITTER_CODE,
                  (SELECT PARTNER_VALUE
                    FROM EDI_TRANSLATION_DETAIL 
                    
                    WHERE ETH_UID IN (
                    
                    SELECT ETH_UID FROM EDI_TRANSLATION_HEADER
                    WHERE CODE_SET='IGMAUTHREP'
                    
                    ) AND SEALINER_VALUE=(select PIOFFC from itp040 WHERE PICODE=P_I_V_POL)  -- fsc 
                    AND ROWNUM=1 AND RECORD_STATUS='A')                     AUTHORIZ_REP_CODE,    
                  ''                        SHIPPING_LINE_BOND_NO_R,
                 '1'                        MODE_OF_TRANSPORT,   --let it be one for defolut 
                  ''                        SHIP_TYPE,
                  ''                        CONVEYANCE_REFERENCE_NO,
                  ''                        TOL_NO_OF_TRANS_EQU_MANIF,
                  ''                        CARGO_DESCRIPTION,
                  ''                        BRIEF_CARGO_DES,
                    (SELECT vvdpdt 
                  FROM   ITP063 A 
                  WHERE  VVPCAL = P_I_V_POL
                         AND (P_I_V_SERVICE IS NULL
                                or VVSRVC =P_I_V_SERVICE)
                         AND VVVESS = P_I_V_VESSEL 
                         AND VVVOYN = P_I_V_VOYAGE 
                         AND ( P_I_V_POL_TERMINAL IS NULL 
                                OR VVTRM1 = P_I_V_POL_TERMINAL ) 
                         AND A.VVVERS = 99 
                         AND A.VVFORL IS NOT NULL 
                         AND A.OMMISSION_FLAG IS NULL 
                         AND ROWNUM < 2)                         EXPECTED_DATE,              --NEED TO PUT SUB QURY
                  (SELECT vvdptm 
                  FROM   ITP063 A 
                  WHERE  VVPCAL = P_I_V_POL 
                         AND (P_I_V_SERVICE IS NULL
                              or VVSRVC =P_I_V_SERVICE)
                         AND VVVESS = P_I_V_VESSEL 
                         AND VVVOYN = P_I_V_VOYAGE 
                         AND ( P_I_V_POL_TERMINAL IS NULL 
                                OR VVTRM1 = P_I_V_POL_TERMINAL ) 
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
                    
                    ) AND SEALINER_VALUE=(select PIOFFC from itp040 WHERE PICODE=P_I_V_POL)   -- fsc from port 
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
                  AND PICODE=P_I_V_POL )                   PORT_OF_CALL_NAME,                    --NEED TO PUT SUB QURY
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
                  ) AND SEALINER_VALUE=TEMP_POL_TERMINAL AND ROWNUM=1)            CUSTOM_TERMINAL_CODE, 
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
                ''                                    NET_WEIGHT_METRIC,
                ''                                         NET_PACKAGE
                 --NEW BL SECTION FIELSD 
            --New VesselVoyage-Fields(28-05-2021) by janmejaya
            , (SELECT A.VSCLSN 
                  FROM   ITP060 A 
                  WHERE  A.VSVESS = P_I_V_VESSEL 
                         AND ROWNUM < 2)  VASSELCODE,--IS CALL SIGN / FROM VSL MASTER 
              'F'  EDI,--F (by default) by guru
              'P'  NON_EDI,--P (by default) by guru
              VOYAGE  PARENT_VOY,--keep it blank (its in icl vsl page) by guru
              ' '  VIA_VCN,--We will update (by guru)
              ' '  SUB_TERMIL,--keept it blank (its in ICL vsl page) by guru
              'imovsl'  TYPE_TRANSPORT_MEANS,--10-IMO VSL & 11 - NON IMO VSL(by guru)
              'CONTAINER'  EQUIMENT_TYPE,--CN - CONTAINER ,In local software we need to select from dropdown(by guru)
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
              ' '  REMARK_VESSEL,-- no mapping given by guru
              ''   SENDER_ID,
              ''   RECIEVER_ID,
              ''   AUTHREPRSNTVCD,
              (select PORT_NAME from rcltbls.cam_port where PK_PORT_CODE=TEMP_LAST_PORT_1)  LAST_PORT_1_NAME,
              (select PORT_NAME from rcltbls.cam_port where PK_PORT_CODE=TEMP_LAST_PORT_2)   LAST_PORT_2_NAME,
              (select PORT_NAME from rcltbls.cam_port where PK_PORT_CODE=TEMP_LAST_PORT_3)   LAST_PORT_3_NAME,
              (select PORT_NAME from rcltbls.cam_port where PK_PORT_CODE= TEMP_NEXT_PORT_1)   NEXT_PORT_4_NAME,
              (select PORT_NAME from rcltbls.cam_port where PK_PORT_CODE= TEMP_NEXT_PORT_2)  NEXT_PORT_5_NAME,
              (select PORT_NAME from rcltbls.cam_port where PK_PORT_CODE= TEMP_NEXT_PORT_3)    NEXT_PORT_6_NAME,
              ''                                                                                                                noOfCrew 
          FROM   IDP005 IDP5 
                 INNER JOIN IDP010 IDP10 
                         ON IDP5.SYBLNO = IDP10.AYBLNO 
          WHERE  -- IDP5.DISCHARGE_PORT = P_I_V_POL   AND
                  ( P_I_V_SERVICE IS NULL 
                        OR IDP5.SERVICE = P_I_V_SERVICE ) 
                 AND ( P_I_V_VESSEL IS NULL 
                        OR IDP5.VESSEL = P_I_V_VESSEL ) 
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

                 AND IDP10.AYSORC = 'C' AND ROWNUM = 1  ; 
                

      END RCL_IGM_VESSLE_VOYAGE_GET_MASTER_DATA; 

PROCEDURE RCL_IGM_CREW_EFFECTS_FORMAT(   
                      P_I_V_VESSEL    VARCHAR2 DEFAULT NULL,     
                        P_I_V_VOYAGE    VARCHAR2 DEFAULT NULL,
                        P_I_V_POD       VARCHAR2,
                        P_I_C_CONT_LST  IN      CLOB
        ) 

        IS
      V_SQL varchar(1000);
        BEGIN

       -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);

     V_SQL:='DELETE FROM IGM_CREW_EFFECT_FORMATE   WHERE                    
                    VESSEL=('||''''|| P_I_V_VESSEL||''''||'
                    )AND VOYAGE=('||''''|| P_I_V_VOYAGE||''''||'
                    )AND POD=('||''''|| P_I_V_POD||''''||') ';
      EXECUTE IMMEDIATE  V_SQL; 
     COMMIT; 
     DELETE FROM IGM_DATA_SET_JSON;

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_C_CONT_LST); COMMIT;                      

     UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';




     INSERT INTO IGM_CREW_EFFECT_FORMATE 
     SELECT          BL_NO_PK                            , 
	                 Description_CODE                    , 
	                 Description                         , 
	                 Quantity_On_Board	                 , 
                     Quantity_On_Board_Code              , 
                     Vessel_SrNo                         ,
                     vessel                              ,
			         voyage                              ,
			         pod                                 ,
                     sequence_No                         ,
                     Person_on_Board_sequence_no          
     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( 				 
						    bl_no_pk                                        PATH '$.blNo',
							Description_CODE                                PATH '$.crewEfctDescCdd',
							Description                                     PATH '$.crewEfctsDesc',
							Quantity_On_Board                               PATH '$.crewEfctQntyOnbrd',
							Quantity_On_Board_Code                          PATH '$.crewEfctQntyCdOnbrd',
							Vessel_SrNo                                     PATH '$.crewEfctsVsslSeqNmbr',
                            vessel                                          PATH '$.vessel',
		                   	voyage                                          PATH '$.voyage',
			                pod                                             PATH '$.pod',
                            sequence_No                                     PATH '$.sequenceNo',
                            Person_on_Board_sequence_no                     PATH '$.personOnBoardSequenceNo'
                        )
           );
      COMMIT;

        END RCL_IGM_CREW_EFFECTS_FORMAT;



	PROCEDURE RCL_IGM_SHIP_STORE_FORMAT(   
                        P_I_V_VESSEL    VARCHAR2 DEFAULT NULL,     
                        P_I_V_VOYAGE    VARCHAR2 DEFAULT NULL,
                        P_I_V_POD       VARCHAR2,
                        P_I_C_CONT_LST  IN      CLOB
        ) 

        IS
      V_SQL varchar(1000);
        BEGIN

       -- insert into IGM_DATA_SET_JSON values(P_I_C_CONT_LST);

     V_SQL:='DELETE FROM IGM_SHIP_STORE_FORMAT    WHERE                    
                    VESSEL=('||''''|| P_I_V_VESSEL||''''||')
                    AND VOYAGE=('||''''|| P_I_V_VOYAGE||''''||')
                    AND POD=('||''''|| P_I_V_POD||''''||') ';
      EXECUTE IMMEDIATE  V_SQL; 
     COMMIT; 
     DELETE FROM IGM_DATA_SET_JSON;

     INSERT INTO IGM_DATA_SET_JSON VALUES (P_I_C_CONT_LST); COMMIT;                      

     UPDATE IGM_DATA_SET_JSON SET DATA_SET = '[' || REPLACE(REPLACE(DATA_SET, '[', ''), ']', '') || ']';




     INSERT INTO IGM_SHIP_STORE_FORMAT 
     SELECT         bl_no_pk          ,
					article_name_code ,
					artice            ,
					location          ,
					quantity_on_board ,
					quantity_on_board_code ,
                    vessel                             ,
			         voyage                             ,
			         pod             ,
                     vessel_srno
     FROM  JSON_TABLE
           (
            (SELECT DATA_SET FROM IGM_DATA_SET_JSON), '$[*]'
                COLUMNS ( 				 
						    bl_no_pk                                         PATH '$.blNo',
							article_name_code                                PATH '$.articleNameCdd',
							artice                                           PATH '$.articleNameText',
							location                                         PATH '$.locOnbrdText',
							quantity_on_board                                PATH '$.qntyOnbrd',
							quantity_on_board_code                           PATH '$.qntyCdOnbrd',
                            vessel                                          PATH '$.vessel',
		                   	voyage                                          PATH '$.voyage',
			                pod                                             PATH '$.pod',
                            vessel_srno                                     PATH '$.vesselSrno'

                        )
           );
      COMMIT;  

        END RCL_IGM_SHIP_STORE_FORMAT;


PROCEDURE RCL_IGM_GET_DELETE_CSV_DATA(
 
                P_I_V_BL            VARCHAR2 DEFAULT NULL,
                P_I_V_VESSEL        VARCHAR2 DEFAULT NULL,
                P_I_V_VOYAGE        VARCHAR2 DEFAULT NULL,
                P_I_V_POD           VARCHAR2 DEFAULT NULL,
                P_I_V_CSV_Check     VARCHAR2 DEFAULT NULL

            )  IS
      V_SQL varchar(1000);
        BEGIN



      IF P_I_V_CSV_CHECK = 'P' THEN
         V_SQL := 'DELETE FROM IGM_PERSON_ON_BOARD WHERE vessel = '''|| P_I_V_VESSEL||''''||' AND voyage = '''|| P_I_V_VOYAGE||''''||' AND POD = '''|| P_I_V_POD||''''  ;
      ELSIF P_I_V_CSV_CHECK = 'C' THEN
             V_SQL := ' DELETE FROM IGM_CREW_EFFECT_FORMATE WHERE  vessel = '''|| P_I_V_VESSEL||''''||' AND voyage = '''|| P_I_V_VOYAGE||''''||' AND POD = '''|| P_I_V_POD||''''  ;
      ELSE
          V_SQL := ' DELETE FROM IGM_Ship_Store_Format   WHERE  vessel = '''|| P_I_V_VESSEL||''''||' AND voyage = '''|| P_I_V_VOYAGE||''''||' AND POD = '''|| P_I_V_POD||''''  ;
       END IF;

        EXECUTE IMMEDIATE  V_SQL; 
       commit;

        END RCL_IGM_GET_DELETE_CSV_DATA;


PROCEDURE RCL_IGM_GET_SAVE_PERSON_ON_BOARD(
                                 P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
                               -- P_I_V_BL            VARCHAR2 DEFAULT NULL,
								P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,     
								P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
								P_I_V_POD          VARCHAR2 DEFAULT NULL,
                                P_O_V_ERROR         OUT VARCHAR2)     IS 


                    l_v_sql VARCHAR2(10000);               

        BEGIN   



l_v_sql:='                 
SELECT BL_NO_PK,
TYPE_CODE,
FAMILY_NAME,
GIVEN_NAME,
NATIONALITY_CODE,
INTRANSIT_INDICATOR,      
CREW_MEMBER_RANKOR_RATING,
CREW_MEMBER_RANKORRATING_CODE,
PORTOFEMBARKATIONCODE,
PORT_OF_EMBARKATION_NAME,
PORT_OF_DISEMBARKATION_CODE,
PORT_OF_DISEMBARKATION_NAME,
GENDER_CODE,
DATE_OF_BIRTH,
PLACE_OF_BIRTHNAME,
COUNTRY_OF_BIRTH_CODE,
IDENTITY_DOC_EXPIRY_DATE,
IDENTITY_OR_TRAVEL_DOC_ISSUING_NATION_CODE,
IDENTITY_OR_TRAVEL_DOC_NMBR,
IDENTITY_OR_TRAVEL_DOC_TYPE_CODE,
VISA,
PNR_NUMBER,
VESSEL,
VOYAGE,
POD
FROM IGM_PERSON_ON_BOARD WHERE                    
                    VESSEL=('||''''|| P_I_V_VESSEL||''''||')AND VOYAGE=('||''''|| P_I_V_VOYAGE||''''||')AND POD=('||''''|| P_I_V_POD||''''||') ';

 --BL_NO_PK IN ('||P_I_V_BL||')';

    OPEN P_O_REFIGMTABFIND FOR l_v_sql;
      END RCL_IGM_GET_SAVE_PERSON_ON_BOARD;


PROCEDURE RCL_IGM_GET_SAVE_CREW_EFFECT(
                                 P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
                                --P_I_V_BL            VARCHAR2 DEFAULT NULL,
								P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,     
								P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
								P_I_V_POD VARCHAR2 DEFAULT NULL,
                                P_O_V_ERROR         OUT VARCHAR2)     IS  


                    l_v_sql VARCHAR2(10000);               

        BEGIN   


          l_v_sql:='                 
SELECT 
BL_NO_PK,
DESCRIPTION_CODE,
DESCRIPTION,
QUANTITY_ON_BOARD,
QUANTITY_ON_BOARD_CODE, 
VESSEL_SRNO,
VESSEL,
VOYAGE,
POD
FROM IGM_CREW_EFFECT_FORMATE WHERE
                   VESSEL=('||''''|| P_I_V_VESSEL||''''||')AND VOYAGE=('||''''|| P_I_V_VOYAGE||''''||')';

    OPEN P_O_REFIGMTABFIND FOR l_v_sql;
      END RCL_IGM_GET_SAVE_CREW_EFFECT;

PROCEDURE RCL_IGM_GET_SAVE_SHIP_STORE(
                                 P_O_REFIGMTABFIND   OUT SYS_REFCURSOR,                                 
                                --P_I_V_BL            VARCHAR2 DEFAULT NULL,
								P_I_V_VESSEL       VARCHAR2 DEFAULT NULL,     
								P_I_V_VOYAGE       VARCHAR2 DEFAULT NULL, 
								P_I_V_POD VARCHAR2 DEFAULT NULL,
                                P_O_V_ERROR         OUT VARCHAR2)     IS 


                    l_v_sql VARCHAR2(10000);               

        BEGIN   


          l_v_sql:='                 
SELECT 
BL_NO_PK,
ARTICLE_NAME_CODE,
ARTICE,
LOCATION,
QUANTITY_ON_BOARD,
QUANTITY_ON_BOARD_CODE,
VESSEL,
VOYAGE,
POD,
vessel_srno
FROM IGM_SHIP_STORE_FORMAT
 
WHERE  
  VESSEL=('||''''|| P_I_V_VESSEL||''''||')AND VOYAGE=('||''''|| P_I_V_VOYAGE||''''||')';

-- BL_NO_PK IN ('||P_I_V_BL||')';

    OPEN P_O_REFIGMTABFIND FOR l_v_sql;
      END RCL_IGM_GET_SAVE_SHIP_STORE;    


PROCEDURE RCL_IGM_APPLY_IGM(

                P_I_V_BL            VARCHAR2 DEFAULT NULL,
                P_I_V_IGM_NUMBER      VARCHAR2 DEFAULT NULL,
				P_I_V_IGM_DATE     VARCHAR2 DEFAULT NULL

            )  IS
      V_SQL varchar(1000);
        BEGIN
		  V_SQL := 'UPDATE IGM_VESSEL_VOYAGE_DETAILES SET IGM_NUMBER = '''|| P_I_V_IGM_NUMBER||''' , IGM_DATE =  '''|| P_I_V_IGM_DATE||''' 
		  WHERE PK_BL_NO= '''|| P_I_V_BL||''' ';

		 EXECUTE IMMEDIATE  V_SQL; 
       commit;

        END RCL_IGM_APPLY_IGM;      

END RCL_IGM_VESEEL_VOYOAGE_INFO_EXPORT;