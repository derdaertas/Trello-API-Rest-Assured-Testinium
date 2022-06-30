package client;

import http.refactored.annotations.*;
import http.refactored.entities.RestMethod;

import static io.restassured.http.ContentType.JSON;

@ServiceDomain("https://api.trello.com/1/")
@QueryParameters({
        @QueryParameter(name = "key", value = "f52b1e439a57a95aae52010d34d57e36"),
        @QueryParameter(name = "token", value = "1cd9b7adb9e239c1b985c863349da5b23972c67d3783f356b9f97191ba46d861")
})
public class TrelloClient {

    @ContentType(JSON) @Resource("/boards")
    public static RestMethod boards;

    @ContentType(JSON) @Resource("/boards/{board_id}")
    public static RestMethod board;

    @ContentType(JSON) @Resource("/boards/{board_id}/cards")
    public static RestMethod getBoardCardsList;

    @ContentType(JSON) @Resource("/boards/{board_id}/cards/{short_card_id}")
    public static RestMethod getBoardCardById;

    @ContentType(JSON) @Resource("/members")
    public static RestMethod membersList;

    @ContentType(JSON) @Resource("/cards")
    public static RestMethod cardsList;

    @ContentType(JSON) @Resource("/1/boards/{board_id}")
    public static RestMethod deleteBoards;

    @ContentType(JSON) @Resource("/1/cards/{card_id}")
    public static RestMethod deleteCard;

    @ContentType(JSON) @Resource("/cards/{card_id}/actions/comments")
    public static RestMethod postNewCommentToCard;

    @Headers({@Header(name = "headerName2", value = "headerValue2"),
            @Header(name = "headerName1", value = "headerValue1")})
    @QueryParameter(name = "testParameterName", value = "TestParValue")
    @ContentType(JSON) @Resource("/cards/{card_id}")
    public static RestMethod getCard;
}
