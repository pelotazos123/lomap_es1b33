
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class LogIn extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://www.googleapis.com")
    .inferHtmlResources(AllowList(), DenyList(""".*\.css""", """.*\.js""", """.*\.gif"""))
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/114.0")
  
  private val headers_0 = Map(
  		"Access-Control-Request-Headers" -> "authorization,client-id,client-integrity,client-session-id,client-version,x-device-id",
  		"Access-Control-Request-Method" -> "POST",
  		"Origin" -> "https://www.twitch.tv",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "same-site"
  )
  
  private val headers_1 = Map(
  		"Accept-Language" -> "es-ES",
  		"Client-Id" -> "kimne78kx3ncx6brgo4mv6wki5h1ko",
  		"Client-Integrity" -> "v4.public.eyJjbGllbnRfaWQiOiJraW1uZTc4a3gzbmN4NmJyZ280bXY2d2tpNWgxa28iLCJjbGllbnRfaXAiOiI3Ny4yMjguMTc5LjU5IiwiZGV2aWNlX2lkIjoiclJPNXpXT3oxbVZqZ0FCYllGRUNCYWNTVlEzbXRPQW0iLCJleHAiOiIyMDIzLTA3LTA0VDE0OjQ2OjI5WiIsImlhdCI6IjIwMjMtMDctMDNUMjI6NDY6MjlaIiwiaXNfYmFkX2JvdCI6ImZhbHNlIiwiaXNzIjoiVHdpdGNoIENsaWVudCBJbnRlZ3JpdHkiLCJuYmYiOiIyMDIzLTA3LTAzVDIyOjQ2OjI5WiIsInVzZXJfaWQiOiI1NTE5NTQ2MSJ9jfsdwA6cHoWzsChSZc4TiW4y-Es5CbqDVQNlu8v7uURAF70qYwPBdXZkk9_vQTcPsmnFZCDTmEudIYG8Q3BCDA",
  		"Client-Session-Id" -> "3be5fcdef630b040",
  		"Client-Version" -> "6f44026b-5336-4daf-9900-c8dcd35ba9b6",
  		"Content-Type" -> "text/plain;charset=UTF-8",
  		"Origin" -> "https://www.twitch.tv",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "same-site",
  		"X-Device-Id" -> "rRO5zWOz1mVjgABbYFECBacSVQ3mtOAm",
  		"authorization" -> "OAuth n9w1h9orp8f316jgkr34h9oqz2y4zv"
  )
  
  private val headers_2 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "same-origin",
  		"Sec-Fetch-User" -> "?1",
  		"Upgrade-Insecure-Requests" -> "1"
  )
  
  private val headers_3 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin"
  )
  
  private val headers_4 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"If-Modified-Since" -> "Fri, 16 Jun 2023 01:17:31 GMT",
  		"If-None-Match" -> """W/"5e2-188c1c76875"""",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin"
  )
  
  private val headers_5 = Map(
  		"If-None-Match" -> """W/"4ac-ibs7FMDcT/LugQv7+54WcO8A360"""",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_6 = Map(
  		"Content-Type" -> "application/json",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_8 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "cross-site",
  		"Sec-Fetch-User" -> "?1",
  		"Upgrade-Insecure-Requests" -> "1"
  )
  
  private val headers_10 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"If-Modified-Since" -> "Fri, 16 Jun 2023 01:17:31 GMT",
  		"If-None-Match" -> """W/"3a89-188c1c76857"""",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin"
  )
  
  private val headers_12 = Map(
  		"DPoP" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL2lucnVwdC5uZXQvdG9rZW4iLCJodG0iOiJQT1NUIiwianRpIjoiOWE2MzgwNmEtMDQxOC00ZWQ1LWE5Y2UtM2ZhNWYzZDBiYzRiIiwiaWF0IjoxNjg4NDI0OTAwfQ.pbTEIaU8DxQ2QX1bLIWosyAs0vDFFkYn8dd6nYbB2kcADeUObyr-kQ2NOah9s_aPANjR3oHO9K2wLzSLbk6aCA",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_13 = Map(
  		"If-None-Match" -> """W/"c12-Kvq8CS2lBBOkyBlGjEmnUK4oFkY"""",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_14 = Map(
  		"Accept" -> "text/turtle",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_18 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjU2YmRhODc5LTdjZGEtNDk4Ny05MDE5LWViY2RiMmM0YjE4MiIsImlhdCI6MTY4ODQyNDkwMX0.C0M8qzs80lDtp1MyeusQ3mLwbBCsqwQim4HJh8b7s8tgPXIeby8tuN7JZPAEiIEdWtmq4288MhKBd2PBGaLiqw"
  )
  
  private val headers_20 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjUwODE0OGVjLWE4MDQtNGJjNy1iYjU1LTM5YjEyM2IzMjc2OSIsImlhdCI6MTY4ODQyNDkwMX0.FAV7GDVx5tRFyZ-_SSzRYJd3AZpkX947rtfHIn42dkIqqGGIVSMTZXJaJ_BzqZ2fryoiUT40pQyNV04xvPnhPg"
  )
  
  private val headers_21 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3BhYmxpdG8zMDAwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjI0OGZkNmM4LTNlMTAtNDUzNC04ZDE4LWQ5ZDZiZTk4MzYxMiIsImlhdCI6MTY4ODQyNDkwMX0.5ZvOXfyxj0pARSdnvzjDTYLNT3XPkgwTh3cex_018EUSc-w6TAB-XQaZih4Y32ouptVl5bcag_D55n-GcNYEtQ"
  )
  
  private val headers_22 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjFhY2Y5YzlmLWY4NTEtNDI2OS05YTM1LTBkY2M2ZjNhYzM0YiIsImlhdCI6MTY4ODQyNDkwMn0.3N3VjAqjxfucgYaPLXP9eJUbYkiYzRJ3ybT52-PckkUwmOT5cBafGCa6pWr5OM5MP0z5SfHKJ2KZy7cm-Mh13A"
  )
  
  private val headers_23 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3BhYmxpdG8zMDAwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjUwNDdhMDA1LTRhYTMtNGM0OC1hZGUxLWUwYzkwOWFiZTNiYyIsImlhdCI6MTY4ODQyNDkwMX0.vWYM8Kux-liM1T-hc54-IS8EV2KNNN9eAuKOGyGGajP-Wa_VmFX8frcsWsqnFoJzS6IwmeufKAn-y5Tdd1r6_w"
  )
  
  private val headers_24 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6ImIzOGQxZTk1LTkxYzQtNGJjZC1hNjBiLWU4YWYwODcyZTQzOCIsImlhdCI6MTY4ODQyNDkwM30.YDaC5Dvj40gkMN4Qta_qnSyyz-virdaWNjnRp90SRzAX6NF7pVV3gMqXb3mD7pUVDARcHJTBH48DnQXBEE4nyg"
  )
  
  private val headers_25 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_26 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6Ijc5YTk5MzM5LWUzYzgtNDg2Yi05MTk2LTQzYjY0ZjBiOWM1OSIsImlhdCI6MTY4ODQyNDkwM30.Zw1_j1dO8lBTwndTW25XjW4fiCjXk84G2_rJNQLQZWmcP1JEwUTR1pG2fiVsJP9PF9DIThtvz0as8HxbHfzndA"
  )
  
  private val headers_27 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjBhMTEyMzdkLWQ0MGQtNDkyZS1iNWVjLTdiN2Y1MjJhNjkzNiIsImlhdCI6MTY4ODQyNDkwNH0.T4osbUTaNMb319zf6A2f0A44R6uouHJSXdlM0bu_8QrG63iAbzJOUCbY8mUvccng9ZtkZF-XjptBQIqHeB2hbA"
  )
  
  private val headers_28 = Map(
  		"Content-Type" -> "application/json; charset=UTF-8",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_29 = Map(
  		"Sec-Fetch-Dest" -> "script",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_31 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzQ1MDQsImlhdCI6MTY4ODQyNDkwNCwianRpIjoiMDA3ODNjY2E0YjNlYjYyYSIsImNuZiI6eyJqa3QiOiJaNlcwNVNvUkFnRnZlZkttX3RIMFZBRHVJbjBLc3gzTTE5cExVbGljcmFRIn0sImNsaWVudF9pZCI6ImJjYjY0NzUzZTZhY2ViYjJiYzE0OTE0NGRmYjllNWMxIiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.bOszVmzx3SrBrCrrDh_yS84zgSc_4j_vCM0B23KLeojdYYyh99Q5Pv88bfQUgOM3XpwL7ygnJa3S1gEt0Nfktek3U7ygqtLfd5jlusXPDAQo5N2ztAiAi96Xr_105uIo9NmHYHqCPauZt8FqSm-zMC5j7YRE4ZyLR78QMe5UkV3Hmb9L_8F613SUQd1cLMSUtFz73Z-zMFflgq5bkYwmcahXwR15i1VTsW4e7YfCsQ2CWYLysKCOtHzyQlcr1gZvjRi7bRqe0VP1bVR3sTrGX9N2c6WyePwsPq_pADyGgKr6szf8hhVMYY0goJ2UMugV0lzT23tbg2uVIlvOxr4Iqg",
  		"content-type" -> "application/json;charset=utf-8",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IjhJdGtJYzdEUUFBS2ViNWVPc1VvbzN2dWhtSXpnYzdBMEJIV1JXd0RpdnciLCJ5IjoiLXByXzRraTZrQkFqSS1uQnhiWmpKcWNmQ3NiQ25VSVJ4ZEQwMnJkejZBayIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IlBVVCIsImp0aSI6ImYyOWU0NWNhLTJiOWUtNGQ1YS1hYmE2LWY0Yjg5ZjdmNDMwMiIsImlhdCI6MTY4ODQyNDkwN30.VVTdR1m5xbMJhw3SjQ01_zYaHNBabmoOvQZvl8c99Q26jlM2DrXVGDpBIKyxcB11truYIbWkaAd4PY1O8_VF7w"
  )
  
  private val headers_32 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"If-Modified-Since" -> "Fri, 16 Jun 2023 01:17:31 GMT",
  		"If-None-Match" -> """W/"3ef-188c1c76855"""",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin"
  )
  
  private val headers_33 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"If-Modified-Since" -> "Fri, 16 Jun 2023 01:17:31 GMT",
  		"If-None-Match" -> """W/"1dc-188c1c76858"""",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin"
  )
  
  private val uri1 = "https://maps.googleapis.com/maps/api/js"
  
  private val uri2 = "https://inrupt.net"
  
  private val uri3 = "https://pablito3000.inrupt.net/public/lomap/markers.json"
  
  private val uri4 = "https://localhost"
  
  private val uri5 = "https://uo276220.inrupt.net"
  
  private val uri7 = "https://gql.twitch.tv/gql"

  private val scn = scenario("LogIn")
    .exec(
      http("request_0")
        .options(uri7)
        .headers(headers_0)
        .resources(
          http("request_1")
            .post(uri7)
            .headers(headers_1)
            .body(RawFileBody("login/0001_request.json"))
        )
    )
    .pause(1)
    .exec(
      http("request_2")
        .get(uri4 + "/")
        .headers(headers_2)
        .resources(
          http("request_3")
            .get(uri4 + "/img/logo-no-background.png")
            .headers(headers_3),
          http("request_4")
            .get(uri4 + "/img/uk-flag.png")
            .headers(headers_4)
        )
    )
    .pause(4)
    .exec(
      http("request_5")
        .get(uri2 + "/.well-known/openid-configuration")
        .headers(headers_5)
        .resources(
          http("request_6")
            .post(uri2 + "/register")
            .headers(headers_6)
            .body(RawFileBody("login/0006_request.json")),
          http("request_7")
            .get(uri2 + "/.well-known/openid-configuration")
            .headers(headers_5),
          http("request_8")
            .get(uri2 + "/authorize?client_id=bcb64753e6acebb2bc149144dfb9e5c1&redirect_uri=https%3A%2F%2Flocalhost&response_type=code&scope=openid%20offline_access%20webid&state=30caa77bf548469884bc361a1dcf6b31&code_challenge=iNvSUAG3Myc-gM8CSZw-xBLivAD0QynvbMdUbTLHPIY&code_challenge_method=S256&prompt=consent&response_mode=query")
            .headers(headers_8),
          http("request_9")
            .get(uri4 + "/img/uk-flag.png")
            .headers(headers_4),
          http("request_10")
            .get(uri4 + "/img/logo-no-background.png")
            .headers(headers_10),
          http("request_11")
            .get(uri2 + "/.well-known/openid-configuration")
            .headers(headers_5),
          http("request_12")
            .post(uri2 + "/token")
            .headers(headers_12)
            .formParam("grant_type", "authorization_code")
            .formParam("redirect_uri", "https://localhost")
            .formParam("code", "ed2153e1334daf93ecae41079dd3daec")
            .formParam("code_verifier", "3ea6b557c84a49cf8312e18ab392d0d4600f392bd0b2496c826d5c6dc48b1070bcf950f144b34afa8182f02ceb6a6d5f")
            .formParam("client_id", "bcb64753e6acebb2bc149144dfb9e5c1")
            .basicAuth("bcb64753e6acebb2bc149144dfb9e5c1","ac47ef62dfacc7df5120e497543f9473"),
          http("request_13")
            .get(uri2 + "/jwks")
            .headers(headers_13),
          http("request_14")
            .get(uri5 + "/profile/card")
            .headers(headers_14),
          http("request_15")
            .get(uri5 + "/profile/card")
            .headers(headers_14),
          http("request_16")
            .get(uri5 + "/profile/card")
            .headers(headers_14),
          http("request_17")
            .get(uri4 + "/img/statts.png")
            .headers(headers_3),
          http("request_18")
            .get(uri5 + "/public/lomap/markers.json")
            .headers(headers_18),
          http("request_19")
            .get(uri5 + "/profile/card")
            .headers(headers_14),
          http("request_20")
            .get(uri5 + "/public/lomap/markers.json")
            .headers(headers_20),
          http("request_21")
            .get(uri3)
            .headers(headers_21),
          http("request_22")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_22),
          http("request_23")
            .get(uri3)
            .headers(headers_23),
          http("request_24")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_24),
          http("request_25")
            .get(uri5 + "/profile/yo.jpg")
            .headers(headers_25),
          http("request_26")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_26),
          http("request_27")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_27)
        )
    )
    .pause(1)
    .exec(
      http("request_28")
        .post("/geolocation/v1/geolocate?key=key=AIzaSyB2h2OuRcUgya5N-5sadfsdhsZqiPWfsfs6sH3n_rptiQ")
        .headers(headers_28)
        .resources(
          http("request_29")
            .get(uri1 + "/AuthenticationService.Authenticate?1shttps%3A%2F%2Flocalhost%2F&4sAIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&8b0&callback=_xdc_._ukdd7d&key=key=AIzaSyB2h2OuRcUgya5N-5sadfsdhsZqiPWfsfs6sH3n_rptiQ&token=80649")
            .headers(headers_29),
          http("request_30")
            .get(uri1 + "/GeocodeService.Search?5m2&1d0&2d0&7sES&9ses&callback=_xdc_._2bc7mp&key=key=AIzaSyB2h2OuRcUgya5N-5sadfsdhsZqiPWfsfs6sH3n_rptiQ&token=36257")
            .headers(headers_29),
          http("request_31")
            .put(uri5 + "/public/lomap/markers.json")
            .headers(headers_31)
            .body(RawFileBody("login/0031_request.txt")),
          http("request_32")
            .get(uri4 + "/img/home.png")
            .headers(headers_32),
          http("request_33")
            .get(uri4 + "/img/marker.png")
            .headers(headers_33),
          http("request_34")
            .get(uri1 + "/ViewportInfoService.GetViewportInfo?1m6&1m2&1d43.326549494191745&2d-5.985528487011194&2m2&1d43.400444907611636&2d-5.749539908670251&2u15&4ses&5e0&6sm%40652000000&7b0&8e0&12e1&13shttps%3A%2F%2Flocalhost%2Fmap&14b1&callback=_xdc_._gynqso&key=key=AIzaSyB2h2OuRcUgya5N-5sadfsdhsZqiPWfsfs6sH3n_rptiQ&token=114520")
            .headers(headers_29)
        )
    )

	setUp(scn.inject(atOnceUsers(50))).protocols(httpProtocol)
}
