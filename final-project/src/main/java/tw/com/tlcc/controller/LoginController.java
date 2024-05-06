package tw.com.tlcc.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Optional;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.com.tlcc.domain.MemberBean;
import tw.com.tlcc.service.MemberRepositoryService;
import tw.com.tlcc.util.DatetimeConverter;


@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174"}, allowCredentials = "true")
@RestController
@RequestMapping(path= {"/login"})
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private MemberRepositoryService  memberRepositoryService;
	
	@RequestMapping(path = "/getSession", method = RequestMethod.GET)
	public String getSession(@Autowired HttpServletRequest request, @Autowired HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
        //使用request物件的getSession()取得session，如果session不存在則建立一個
        HttpSession session = request.getSession();
        //取得session的Id
        String sessionId = session.getId();
        
        //判斷session是不是新建的
        if (session.isNew()) { //如果是新的，預設loginStatus為no，回傳字串new
        	System.out.println("\n檢查點1: 建立新SESSION，查無舊SESSION");
            System.out.println("新session建立成功，新session的id是："+sessionId);
            //將資料儲存到session中
            session.setAttribute("loginStatus", "no");
            System.out.println("<br>loginStatus: 預設成為 no");
            return "new session created";
        
        } else { //如果是舊的，loginStatus可能為no或是yes，取得loginStatus的值之後回傳結果字串
        	System.out.println("\n檢查點2: 舊SESSION存在");
        	//有舊Session，但不知道是否已經登入，要檢查
            session.getAttribute("loginStatus");
            Optional<String> optional = Optional.ofNullable((String)session.getAttribute("loginStatus"));
            if(optional.isPresent() && !optional.isEmpty()) {
            	System.out.print("loginStatus："+ optional.get());
            	if(optional.get().equalsIgnoreCase("no")) {
            		return "old session exists, loginStatus= no";           		
            	}
            	
            	else if(optional.get().equalsIgnoreCase("yes")) {
            		return "old session exists, loginStatus= yes"; 
            	
            	} else {
            		System.out.println("\n optional發生未知錯誤 \n");
            		return "optional發生未知錯誤";
            	}
            } else { //其他錯誤
            	System.out.println("\n 發生未知錯誤 \n");
            	return "發生未知錯誤";
            }
        }
	}
		
	//檢查帳密的方法 
	@RequestMapping(path = "/verify/{id}/{pw}", method = RequestMethod.GET) //接收axios()用GET方法
	public String verify(@PathVariable(name="id") String username, @PathVariable(name="pw") String password, 
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
//		PrintWriter out = response.getWriter();
		String result="fail to get session";;
		
//		String username = request.getParameter("memberId");
//		String password = request.getParameter("memberPassword");
		MemberBean member = memberRepositoryService.findByMemberIdAndMemberPassword(username, password);
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		HttpSession session = request.getSession(false);
		if(session!=null) { //如果有找到一個既有的Session，才進入下一步
			if (member != null) { //如果帳密符合，才進入下一步
				//將會員資訊記在session
				String signUpDate = DatetimeConverter.toString(member.getSignUpDate(), "yyyy-MM-dd");
				String finalLogInDate = DatetimeConverter.toString(member.getFinalLogInDate(), "yyyy-MM-dd");
				session.setAttribute("loginStatus", "yes");
				session.setAttribute("id", member.getId());
				session.setAttribute("memberId", member.getMemberId());
				session.setAttribute("memberPassword", member.getMemberPassword());
				session.setAttribute("memberName", member.getMemberName());
				session.setAttribute("email", member.getEmail());
				session.setAttribute("gender", member.getGender()); //缺
				session.setAttribute("memberAddress", member.getMemberAddress()); //缺
				session.setAttribute("discountPointsLeft", member.getDiscountPointsLeft());
				session.setAttribute("signUpDate", signUpDate);
				session.setAttribute("finalLogInDate", finalLogInDate);
				session.setAttribute("accessLevel", member.getAccessLevel());
				JSONObject item = new JSONObject()
						.put("id", member.getId())
						.put("memberId", member.getMemberId())
						.put("memberName", member.getMemberName())
						.put("memberPassword", member.getMemberPassword())
						.put("email", member.getEmail())
						.put("gender", member.getGender())
						.put("memberAddress", member.getMemberAddress())
						.put("discountPointsLeft", member.getDiscountPointsLeft())
						.put("signUpDate", signUpDate)
						.put("finalLogInUpDate", finalLogInDate)
						.put("accessLevel", member.getAccessLevel())
						;
				array = array.put(item);
				System.out.println("\n帳密吻合，loginStatus值已改為yes，MemberBean各屬性已加入session中\n");
//				out.println("帳密吻合，loginStatus值已改為yes，MemberBean各屬性已加入session中");
				responseJson.put("list", array);
				result= responseJson.toString();
		
			} else {
				System.out.println("\n帳密不吻合\n");
//				out.println("帳密不吻合");
				result= "fail";
			}
		}
//		out.flush();
		return result;
	}
	
	//自己造cookie的範例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody //回傳的不是頁面
    public String setCookie(HttpServletResponse response) {//cookie存到response裡面
        
    	//建立cookie,cookie儲存的是 一個字串，且每個物件只能儲存一個key-value
        Cookie cookie = new Cookie("JSESSIONID", generateUUID());
        //設定cookie生效範圍。 即在哪條路徑下有效，此路徑及子路徑
        cookie.setPath("/");
        //設定生存的時間。 預設是會話級別，存在內存，關閉瀏覽器就死。 設定存活時間後會儲存到硬碟
        cookie.setMaxAge(60 * 60);//60分鐘
        //發送cookie，加入到response，瀏覽器回應的時候就帶著，在回應標頭responseHeaders裡
        response.addCookie(cookie);
        
        Cookie cookie2 = new Cookie("status", generateUUID());
        cookie2.setPath("/");
        cookie2.setMaxAge(60 * 60);//60分鐘
        response.addCookie(cookie2);
        
        Cookie cookie3 = new Cookie("accessLevel", generateUUID());
        cookie3.setPath("/");
        cookie3.setMaxAge(60 * 60);//60分鐘
        response.addCookie(cookie3);
        
        return "設置了一個login用的cookie";
    }
    
    //print出全部cookie
    @RequestMapping(path = "/cookie/show", method = RequestMethod.GET)
    @ResponseBody
    public String showCookies(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();//根据请求数据，找到cookie数组
        StringBuilder cookieInfo = new StringBuilder();
        
        if (null==cookies) {//如果没有cookie数组
            System.out.println("沒找到任何cookie");
            return "沒找到任何cookie";
        } else {
            for(Cookie cookie : cookies){
                System.out.println("cookieName: "+cookie.getName()+" ,cookieValue: "+ cookie.getValue());
                cookieInfo.append("Cookie Name: ").append(cookie.getName());
                cookieInfo.append(", Cookie Value: ").append(cookie.getValue()).append("\n");
            }
        }
        return "有找到cookie: \n"+ cookieInfo;
    }
    
    
    //瀏覽器第二次造訪時，測試是否有上次的cookie。 cookie在請求標頭裡。 只找某一cookie時使用
    @RequestMapping(path = "/cookie/check", method = RequestMethod.GET)
    @ResponseBody
    public String checkCookie(@CookieValue(value = "loginCookie", defaultValue = "") String c) {//只取得名為code的cookie，取出賦值給c
        if(c==null || c.isEmpty()) {
        	return "沒找到 loginCookie";
        }
        
	    System.out.println("cookie value =" + c);
        return "第二次存取伺服器，有找到上次造訪的cookie";
    }
       
    
    
    //列印出session內所有attributes
    @RequestMapping(path = "/getSessionAttr", method = RequestMethod.GET)
    @ResponseBody
    public void sessonAttributes(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	//获取session
    	HttpSession session = request.getSession(false);
    	// 获取session中所有的键值
    	Enumeration<?> enumeration = session.getAttributeNames();
    	// 遍历enumeration
    	while (enumeration.hasMoreElements()) {
    	  // 获取session的属性名称
    	  String name = enumeration.nextElement().toString();
    	  // 根据键值取session中的值
    	  Object value = session.getAttribute(name);
    	  // 打印结果
    	  System.out.print("\n");
    	  System.out.println("name:" + name + ",value:" + value);
    	  response.getWriter().println("Sesson attributes:  " + "key:" + name + ", value:" + value);
    	}
    	response.getWriter().flush();
    }
    
    //亂碼產生器
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
    
    
//封存    
//	@RequestMapping(path = "/getSession", method = RequestMethod.GET)
//	public String getSession(@Autowired HttpServletRequest request, @Autowired HttpServletResponse response) throws IOException {
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=UTF-8");
//		String result="";
//		PrintWriter out = response.getWriter();
//		
//        //使用request物件的getSession()取得session，如果session不存在則建立一個
//        HttpSession session = request.getSession();
//        //取得session的Id
//        String sessionId = session.getId();
//        
//        //判斷session是不是新建的
//        if (session.isNew()) { //如果是新的，預設loginStatus為no，回傳字串new
//        	System.out.println("\nPRINT1: 建立新SESSION，查無舊SESSION");
//            out.println("新session建立成功，新session的id是："+sessionId);
//            //將資料儲存到session中
//            session.setAttribute("loginStatus", "no");
//            out.println("<br>loginStatus: 預設成為 no");
//            result= "new";
//        } else { //如果是舊的，loginStatus可能為no或是yes，取得loginStatus的值之後由result回傳字串
//        	System.out.println("\nPRINT2: 舊SESSION存在");
//        	out.println("伺服器尚存有舊的session了，舊session的id是："+sessionId);
//        	//有舊Session，但不知道是否已經登入，要檢查
//            session.getAttribute("loginStatus");
//            Optional<String> optional = Optional.ofNullable((String)session.getAttribute("loginStatus"));
//            if(optional.isPresent() && !optional.isEmpty()) {
//            	System.out.print("loginStatus："+ optional.get());
//                out.println("<br>loginStatus："+ optional.get());
//                result= "old JSSESSIONID= " + optional.get(); 
//            } else { //其他錯誤
//            	System.out.println("\n 發生未知錯誤 \n");
//            	result= "error";
//            }
//        }
//        out.flush();
//        return result;
//	}
}
