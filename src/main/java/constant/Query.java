package constant;

public class Query {
    public static final String SPORTS_ID_QUERY_USER = "SELECT * FROM users Where name = 'key'";
    public static final String SPORT_TV_QUERY_EVENT_TV_ACTIVE = "SELECT * FROM event_eventtv where is_active = 'key'";
    public static final String SPORT_TV_QUERY_EVENT_TV_NAME = "SELECT * FROM event_eventtv where name ='key'";
    public static final String SPORT_TV_QUERY_EVENT_VIDEO = "SELECT * FROM event_video where is_active = 'key'";
    public static final String SPORT_TV_QUERY_EVENT_VIDEO_TAG = "SELECT * FROM event_video where is_active = 'key' and tag = 'Bundesliga, '";
    public static final String SPORT_TV_QUERY_EVENT_VIDEO_LEAGUE_TAG = "SELECT * FROM event_video where is_active = 'true' and tag = 'vleague 1, '";
    public static final String SPORT_TV_QUERY_SCREEN_BLOCK = "SELECT * FROM screenblock_screenblock where name = 'key'";
    public static final String SPORTS_QUERY_EVENT_TV_SCREEN_BLOCK = "SELECT * FROM event_eventtv_screen_blocks where eventtv_id = 'key'";
    public static final String MON_GO_DB_QUERY_DEVICE = "SELECT * FROM session_device Where user_id = 'key'";
}
