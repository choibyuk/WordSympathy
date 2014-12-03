package kr.or.jaspersoft.android.wordsympathy.common;

public final class Constant {
	
	private Constant() {}
	
    public static final String AUTH_TYPE_GOOGLE = "001";
    public static final String AUTH_TYPE_FACEBOOK = "002";
    public static final String AUTH_TYPE_TWITTER = "003";
    
    public static final String GENDER_MALE = "001";
    public static final String GENDER_FEMALE = "002";
    public static final String GENDER_UNKNOWN = "003";
    
    /** 무작위 단어 */
    public static final String WORD_CATEGORY_RANDOM = "001";
    /** 구글트렌드 단어 */
    public static final String WORD_CATEGORY_TREND = "002";
    /** 최신느낌글 단어 */
    public static final String WORD_CATEGORY_LATEST = "003";
    /** 인기느낌글 단어 */
    public static final String WORD_CATEGORY_POP = "004";
    /** 관리자추천 단어 */
    public static final String WORD_CATEGORY_RECOM = "005";
    /** 사용자맞춤 단어 */
    public static final String WORD_CATEGORY_FIT = "006";
    /** 연관 단어 */
    public static final String WORD_CATEGORY_CONN = "007";
    
    public static final String PAGING_DIRECTIVE_OLD = "old";
    public static final String PAGING_DIRECTIVE_NEW = "new";
    
    public final static char YES = 'Y';
	public final static char NO = 'N';
	
	public final static String SUCCESS = "success";
	public final static String FAIL = "fail";
    
    /** 구글 OAuth 2.0 인증 */
    public static final int HANDLER_WHAT_AUTH_GOOGLE_REQUEST = 90001;
    /** 트위터 OAuth 2.0 대화창 재요청 */
    public static final int HANDLER_WHAT_AUTH_TWITTER_RECALL = 90002;
    /** 트위터 OAuth 2.0 인증 종료 */
    public static final int HANDLER_WHAT_AUTH_TWITTER_SUCCESS = 90003;
    /** 작업 진행중 프로그레스 대화창 보이기 */
	public final static int HANDLER_WHAT_PROGRESS_SHOW = 99998;
	/** 작업 진행중 프로그레스 대화창 숨기기 */
	public final static int HANDLER_WHAT_PROGRESS_HIDE = 99999;
    
    
    
    
    
	
	
	
	
	
	
	
	
	/** for Kakao Talk Link */
	public final static String OWNER = "jaspersoft.or.kr";
	public final static String APP_URL = "market://details?id=kr.or.jaspersoft.android.game.mafia.online";
	public final static String APP_NAME = "마피아 Mobile";
	public final static String APP_FILTER_DATA = "jasper77://online.mafia.jaspersoft.or.kr";
	public final static String WEB_LINK_URL = "http://www.jaspersoft.or.kr";
	public final static String SHARE_MESSAGE = "-마피아 Mobile 앱-";
	public final static String SHARE_MAFIA_START = "마피아의 밤으로 오라!!!";
	
	public final static String CHAT_ACTIVITY_RECEIVER_ACTION_NAME = "kr.or.jaspersoft.android.game.mafia.online.service.MainActivity.ChatBroadcastReceiver";
	public final static String CHAT_SERVICE_RECEIVER_ACTION_NAME = "kr.or.jaspersoft.android.game.mafia.online.service.XMPPChatService.ChatBroadcastReceiver";
	public final static String CHAT_ROSTER_CHANGED_ACTION_NAME = "kr.or.jaspersoft.android.game.mafia.online.service.RosterChanged";
	public final static String CHAT_KIND_NAME = "__chat_message_kind_name__";
	public final static String CHAT_CONTENT_NAME = "__chat_message_content_name__";
	
	/** 대화 속성 - 공개 */
	public final static int CHAT_ATTR_PUBLIC = 0;
	/** 대화 속성 - 비밀 */
	public final static int CHAT_ATTR_PRIVATE = 1;
	
	/** 계정 대화창을 통해 계정 설정 성공 */
	public final static int HANDLER_WHAT_DIALOG_ACCOUNT_SUCCESS = 40000;
	/** 인증 대화창을 통해 인증 성공 */
	public final static int HANDLER_WHAT_DIALOG_AUTH_SUCCESS = 40001;
	/** Operation Display Main Button 클릭 */
	public final static int HANDLER_WHAT_OPTION_ITEM_CLICK = 40002;
	
	/** OAuth access token으로 로그인하기 */
	public final static int CHAT_KIND_XMPP_SIGN_IN = 50000;
	/** 로그인 여부 질의 */
	public final static int CHAT_KIND_XMPP_REQUEST_SIGN_IN = 50001;
	/** 로그인 여부 응답 */
	public final static int CHAT_KIND_XMPP_RESPONSE_SIGN_IN = 50002;
	/** 로그인 성공했을 때 호출 */
	public final static int CHAT_KIND_SIGN_IN_SUCCESS = 50003;
	/** 로그인을 시도했으나 실패했을 때 호출 */
	public final static int CHAT_KIND_SIGN_IN_FAILED = 50004;
	/** 로그인을 시도했으나 실패했을 때 호출[서버 연결이 끊어졌거나 이미 로그인 되어있을 때 발생] */
	public final static int CHAT_KIND_SIGN_IN_FAILED_DISCONNECTED_ALREADY_IN = 50005;
	/** 자동 재로그 */
	public final static int CHAT_KIND_XMPP_AUTO_SIGN_IN = 50006;
	/** 로그인 유지 설정 */
	public final static int CHAT_KIND_XMPP_KEEP_SIGN_INOUT = 50007;
	
	/** 주소록 DB 변동 발생 */
	public final static int CHAT_KIND_ROSTER_DB_CHANGED = 50100;
	/** 친구 Presence 변동 발생 */
	public final static int CHAT_KIND_ROSTER_PRESENCE_CHANGED = 50101;
	/** 친구 추가 */
	public final static int CHAT_KIND_ROSTER_ENTITY_ADD = 50200;
	/** 친구 추가 실패 */
	public final static int CHAT_KIND_ROSTER_ENTITY_ADD_FAILED = 50201;
	/** 친구 수정 */
	public final static int CHAT_KIND_ROSTER_ENTITY_UPDATE = 50210;
	/** 친구 수정 실패 */
	public final static int CHAT_KIND_ROSTER_ENTITY_UPDATE_FAILED = 50211;
	/** 친구 삭제 */
	public final static int CHAT_KIND_ROSTER_ENTITY_DELETE = 50220;
	/** 친구 삭제 실패 */
	public final static int CHAT_KIND_ROSTER_ENTITY_DELETE_FAILED = 50221;
	/** 친구 작업 실패[서버 연결 오류 혹은 미로그인 오류] */
	public final static int CHAT_KIND_ROSTER_ENTITY_TASK_FAILED_LOGGED = 50250;
	
	/** 연결이 생성되었을 때 호출 */
	public final static int CHAT_KIND_CONNECTION_CREATED = 50300;
	/** 연결맺기를 시도했으나 오류가 발생했을 때 호출 */
	public final static int CHAT_KIND_CONNECTION_FAILED = 50301;
	/** 연결이 닫힐 때 호출 */
	public final static int CHAT_KIND_CONNECTION_CLOSED = 50302;
	/** 예기치 못한 오류가 발생하여 연결이 닺혔을 때 호출 */
	public final static int CHAT_KIND_CONNECTION_CLOSED_ON_ERROR = 50303;
	
	/** 연결을 다시 맺기 위해서 몇 초 후에 시도하겠다고 알릴 때 호출 */
	public final static int CHAT_KIND_RECONNECTING_IN = 50400;
	/** 재연결을 시도하다가 실패했을 때 호출 */
	public final static int CHAT_KIND_RECONNECTION_FAILED = 50401;
	/** 재연결에 성공했을 때 호출 */
	public final static int CHAT_KIND_RECONNECTION_SUCCESS = 50402;
	
	/** MUC Room 생성 요청 */
	public final static int CHAT_KIND_ROOM_CREATE_ASK = 50500;
	/** MUC Room 생성 성공시에 호출 */
	public final static int CHAT_KIND_ROOM_CREATE_SUCCESS = 50501;
	/** MUC Room 생성 실패시에 호출 */
	public final static int CHAT_KIND_ROOM_CREATE_FAILED = 50502;
	/** MUC Room 삭제 요청 */
	public final static int CHAT_KIND_ROOM_DESTROY_ASK = 50503;
	/** MUC Room 삭제 성공시에 호출 */
	public final static int CHAT_KIND_ROOM_DESTROY_SUCCESS = 50504;
	/** MUC Room 삭제 실패시에 호출 */
	public final static int CHAT_KIND_ROOM_DESTROY_FAILED = 50505;
	
	/** MUC 사용자 초대 메시지 보내기 요청 */
	public final static int CHAT_KIND_INVITE_SEND = 50600;
	/** MUC 사용자 초대 메시지 보내기 성공시에 호출 */
	public final static int CHAT_KIND_INVITE_SEND_SUCCESS = 50601;
	/** MUC 사용자가 초대에 응해 채팅방에 참여시에 호출 */
	public final static int CHAT_KIND_JOIN_BY_INVITE = 50602;
	/** MUC Room 참여 성공시에 호출 */
	public final static int CHAT_KIND_JOIN_SUCCESS = 50603;
	/** MUC Room 참여 실패시에 호출 [일반적인 실패] */
	public final static int CHAT_KIND_JOIN_FAILED = 50604;
	/** MUC Room 참여 실패시에 호출 [패스워드 미입력] */
	public final static int CHAT_KIND_JOIN_FAILED_PASSWORD_REQUIRED = 50605;
	/** MUC Room 참여 실패시에 호출 [채팅방 입장이 금지된 유저] */
	public final static int CHAT_KIND_JOIN_FAILED_USER_BANNED = 50606;
	/** MUC Room 참여 실패시에 호출 [채팅방이 존재하지 않거나 잠김] */
	public final static int CHAT_KIND_JOIN_FAILED_ROOM_NOTEXIST_LOCKED = 50607;
	/** MUC Room 참여 실패시에 호출 [미등록된 사용자] */
	public final static int CHAT_KIND_JOIN_FAILED_REGIST_REQUIRED = 50608;
	/** MUC Room 참여 실패시에 호출 [Nickname 중복] */
	public final static int CHAT_KIND_JOIN_FAILED_NICKNAME_DUPLICATED = 50609;
	/** 다른 사용자가 자신을 초대했을 때 호출 */
	public final static int CHAT_KIND_INVITATION_RECEIVED = 50610;
	/** 본인이 초대한 사용자가 초대를 거절했을 때 호출 */
	public final static int CHAT_KIND_INVITATION_DECLINED = 50611;
	/** 대화방에서 방출되었을 때 호출 */
	public final static int CHAT_KIND_KICKED = 50612;
	/** 다른 사용자의 초대를 수락할 때 호출 */
	public final static int CHAT_KIND_INVITATION_SEND_AGREE = 50613;
	/** 다른 사용자의 초대를 거절할 때 호출 */
	public final static int CHAT_KIND_INVITATION_SEND_DECLINE = 50614;
	/** 멤버를 방출(강제퇴장) 요청 확인 */
	public final static int CHAT_KIND_KICK_SEND_CONFIRM = 50615;
	/** 멤버를 방출(강제퇴장) 요청 호출 */
	public final static int CHAT_KIND_KICK_SEND = 50616;
	/** 멤버 방출 요청에 성공했을 때 호출 */
	public final static int CHAT_KIND_KICK_SEND_SUCCESS = 50617;
	/** 멤버 방출 요청에 실패했을 때 호출 */
	public final static int CHAT_KIND_KICK_SEND_FAILED = 50618;
	/** 대화방에 입장한 목록을 조회했을 때 호출 */
	public final static int CHAT_KIND_PARTICIPANTS_RETRIEVED = 50619;
	/** 멤버 방출 알림을 수신했을 때(자신이 아닌 타인) 호출 */
	public final static int CHAT_KIND_KICKED_WHOM = 50620;
	/** 사용자 다시 초대하기 요청 확인 */
	public final static int CHAT_KIND_REINVITE_CONFIRM = 50621;
	/** 사용자 다시 초대하기 요청 호출 */
	public final static int CHAT_KIND_REINVITE_SEND = 50622;
	/** 마피아 방 나가기 요청 호출 */
	public final static int CHAT_KIND_ROOM_OUT_SEND = 50623;
	/** 마피아 방 나가기 요청 성공했을 때 호출 */
	public final static int CHAT_KIND_ROOM_OUT_SUCCESS = 50624;
	/** 다른 사용자가 마피아 방 나가기했을 때 호출 */
	public final static int CHAT_KIND_ROOM_OUT_WHOM = 50625;
	
	/** 메시지 전송 요청했을 때 호출 */
	public final static int CHAT_KIND_MESSAGE_SEND_ASK = 50700;
	/** 메시지 전송에 성공했을 때 호출 */
	public final static int CHAT_KIND_MESSAGE_SEND_SUCCESS = 50701;
	/** 메시지 전송에 실패했을 때 호출 */
	public final static int CHAT_KIND_MESSAGE_SEND_FAILED = 50702;
	/** 대화방으로부터 Packet 수신했을 때 호출 */
	public final static int CHAT_KIND_MESSAGE_RECEIVED = 50703;
	
	/** 마피아 게임 시작 요청 */
	public final static int CHAT_KIND_GAME_START_ASK = 50800;
	/** 마피아 게임 시작했을 때 호출 */
	public final static int CHAT_KIND_GAME_START = 50801;
	/** 밤 시작 요청 */
	public final static int CHAT_KIND_NIGHT_START_ASK = 50802;
	/** 밤 시작했을 때 호출 */
	public final static int CHAT_KIND_NIGHT_START = 50803;
	/** 타겟 지정 후 진행자에게 알림 요청 */
	public final static int CHAT_KIND_NOTIFY_TARGET_ASK = 50804;
	/** 밤 임무 수행 결과 알림 */
	public final static int CHAT_KIND_NIGHT_RESULT = 50805;
	/** 게임종료 - 마피아 승리 */
	public final static int CHAT_KIND_VICTORY_MAIFA = 50806;
	/** 게임종료 - 시민 승리 */
	public final static int CHAT_KIND_VICTORY_CITIZEN = 50807;
	/** 낮 시작 요청 */
	public final static int CHAT_KIND_DAY_START_ASK = 50808;
	/** 낮 시작했을 때 호출 */
	public final static int CHAT_KIND_DAY_START = 50809;
	/** 투표 진행 알림 요청 */
	public final static int CHAT_KIND_NOTIFY_VOTE_ASK = 50810;
	/** 투표 진행 알림 - 동의여부 */
	public final static int CHAT_KIND_NOTIFY_VOTE_AGREE = 50811;
	/** 투표 동의 결정 알림 요청 */
	public final static int CHAT_KIND_NOTIFY_DECISION_ASK = 50812;
	/** 투표 최종 결과 알림 */
	public final static int CHAT_KIND_NOTIFY_VOTE_RESULT = 50813;
	/** 마피아 게임 강제 종료 요청 */
	public final static int CHAT_KIND_GAME_FINISH_ASK = 50814;
	/** 마피아 게임 강제 종료 */
	public final static int CHAT_KIND_GAME_FINISH = 50815;
	/** 마피아 온라인 앱 종료 */
	public final static int CHAT_KIND_APP_FINISH = 50816;
	
	/** 대화 목록 새로고침 */
	public final static int HANDLER_WHAT_CHAT_LIST_REFRESH = 99995;
	/** 참가자 목록 새로고침 */
	public final static int HANDLER_WHAT_PARTICIPANT_LIST_REFRESH = 99996;
	/** 로컬 DB 변경이 일어남 */
	public final static int HANDLER_WHAT_DB_CHANGED = 99997;
	
	/** 예/아니오 간단 확인 대화창 타입 - 초대장 수신 */
	public final static int SIMPLE_CONFIRM_TYPE_RECEIVE_INVITATION = 90100;
	/** 예/아니오 간단 확인 대화창 타입 - 방출(강제퇴장) */
	public final static int SIMPLE_CONFIRM_TYPE_KICK = 90101;
	/** 예/아니오 간단 확인 대화창 타입 - 다시 초대 */
	public final static int SIMPLE_CONFIRM_TYPE_REINVITE = 90102;
	/** 예/아니오 간단 확인 대화창 타입 - 방나가기 */
	public final static int SIMPLE_CONFIRM_TYPE_ROOM_OUT = 90103;
	/** 예/아니오 간단 확인 대화창 타입 - 동의여부 투표 */
	public final static int SIMPLE_CONFIRM_TYPE_VOTE_AGREE = 90104;
	/** 예/아니오 간단 확인 대화창 타입 - 게임강제종료 */
	public final static int SIMPLE_CONFIRM_TYPE_GAME_FINISH = 90105;
	/** 예/아니오 간단 확인 대화창 타입 - 앱종료 */
	public final static int SIMPLE_CONFIRM_TYPE_APP_FINISH = 90106;
	
	public final static int ACTIVITY_ADDRESS_CODE = 0;
	public final static int ACTIVITY_SETTING_CODE = 1;
	public final static int ACTIVITY_HELP_CODE = 2;
	
	/** Preference 사용자 정보 저장 명칭 */
	public final static String PREFERENCE_USERINFO = "__preference_userinfo__";
	/** Preference App 설정 저장 명칭 */
	public final static String PREFERENCE_SETTING = "__preference_setting__";
	/** Preference 사용자가 지정한 계정 정보 키 */
	public final static String PREF_KEY_ACCOUNT = "__pref_key_account__";
	/** Preference 사용자가 지정한 계정 비밀번호 정보 키 */
	public final static String PREF_KEY_PASSWORD = "__pref_key_password__";
	/** Preference 사용자가 지정한 별칭 정보 키 */
	public final static String PREF_KEY_NICKNAME = "__pref_key_nickname__";
	/** Preference 사용자가 획득한 JID 정보 키 */
	public final static String PREF_KEY_JID = "__pref_key_jid__";
	/** Preference 사용자가 획득한 Room 정보 키 */
	public final static String PREF_KEY_ROOM = "__pref_key_room__";
	/** Preference 마켓에서 최근에 조회한 버전 코드 키 */
	public final static String PREF_KEY_LATEST_MARKET_VERSION_CODE = "__pref_key_latest_market_version_code__";
	/** Preference 마켓에서 최근에 조회한 버전 이름 키 */
	public final static String PREF_KEY_LATEST_MARKET_VERSION_NAME = "__pref_key_latest_market_version_name__";
	/** Preference 로그인 유지 키 */
	public final static String PREF_KEY_KEEP_SIGNIN = "__pref_key_keep_signin__";
	
	public static final String CHAT_ROOM_STATUS_OPEN = "O";//open
	public static final String CHAT_ROOM_STATUS_CLOSE = "C";//close
	public static final String CHAT_ROOM_STATUS_DELETE = "D";//삭제 되었거나 삭제 대상이어서 사용할 수 없음
	
	public static final int NOTIFICATION_INDEX = 701637;
}
