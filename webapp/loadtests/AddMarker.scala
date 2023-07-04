
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class AddMarker extends Simulation {

  private val httpProtocol = http
    .baseUrl("https://localhost")
    .inferHtmlResources(AllowList(), DenyList(""".*\.css""", """.*\.js""", """.*\.gif"""))
    .acceptHeader("*/*")
    .acceptEncodingHeader("gzip, deflate, br")
    .acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
    .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/114.0")
  
  private val headers_0 = Map(
  		"If-None-Match" -> """W/"4ac-ibs7FMDcT/LugQv7+54WcO8A360"""",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_1 = Map(
  		"Content-Type" -> "application/json",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_3 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "cross-site",
  		"Sec-Fetch-User" -> "?1",
  		"Upgrade-Insecure-Requests" -> "1"
  )
  
  private val headers_4 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"If-Modified-Since" -> "Fri, 16 Jun 2023 01:17:31 GMT",
  		"If-None-Match" -> """W/"3a89-188c1c76857"""",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin"
  )
  
  private val headers_5 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"If-Modified-Since" -> "Fri, 16 Jun 2023 01:17:31 GMT",
  		"If-None-Match" -> """W/"5e2-188c1c76875"""",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin"
  )
  
  private val headers_7 = Map(
  		"DPoP" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL2lucnVwdC5uZXQvdG9rZW4iLCJodG0iOiJQT1NUIiwianRpIjoiZTJiNGRlZjItM2RmNi00N2JlLWExNzUtNjBjZGM0ZWViZjIzIiwiaWF0IjoxNjg4NDI2NjI3fQ.gbDRMCtpoFNJMAIA_gTKWgdFFPF2_tct6VlsWO2_mIZ7sO0jCobdEcoTjLRNNldkOk_0kmlCApVqOJ41vYZglQ",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_8 = Map(
  		"If-None-Match" -> """W/"c12-Kvq8CS2lBBOkyBlGjEmnUK4oFkY"""",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_9 = Map(
  		"Accept" -> "text/turtle",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_12 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "same-origin"
  )
  
  private val headers_14 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjA1YjU4ZGQzLTE5YjEtNDVlYy04MDYxLTIxNmJlYjhhNGE5ZCIsImlhdCI6MTY4ODQyNjYyOX0.PQEmva2PfbrebdsR27SHDr_rJjBO5t5Gvi17hOm5GuDQh29OkclGLy8jTG7xPNy0-aP653OailpoIO0VrXxsaw"
  )
  
  private val headers_15 = Map(
  		"Sec-Fetch-Dest" -> "script",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_17 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3BhYmxpdG8zMDAwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6Ijc2NjcxN2M0LWFlY2EtNGY2NC1hMjE1LTQxNWIzZWRjMDU0MyIsImlhdCI6MTY4ODQyNjYyOX0.3yjr0nlM2d7bSkvKWCeFx1claWqbeiFEjhDXu8S9Ywvb05CbRq3hA7EHG9gqVnuueIWJIc3lM3_3A6JTNRLXGA"
  )
  
  private val headers_18 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"content-type" -> "application/json;charset=utf-8",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IlBVVCIsImp0aSI6IjE5YmMxM2E3LTY3NGQtNDNjMi1iN2Q4LTRjZjNmOGYzMDRjNiIsImlhdCI6MTY4ODQyNjYzMH0.UVLBwxHNr0aTUT_GbWDsOBS0F29k41zgvRDI4tX_6H-hvj5GrLWj96FWUcx6d_kYOVRaLQ5I2A9T268jc2x9gg"
  )
  
  private val headers_19 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjFkNzU3NDBiLTcyNDAtNDA4Yy1iZjBiLTAxNDQ5YzM2ZjY0ZiIsImlhdCI6MTY4ODQyNjYyOX0.w_Sfn8_4lRjJ8shc3OdRFz17GwKW1YK-npbTOkUUmY9vSygaBMF8UgSgct1z6txZPt7Ui6za-Zzx65SVukcODA"
  )
  
  private val headers_23 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjkxYTc2NjJmLTVkNzktNDI1ZC1hNGIzLWUwNTgxMjFjYmYyMiIsImlhdCI6MTY4ODQyNjYzMH0.oFUzHSxIB1ATiDWKrwqv1RMlycrufdTGczcRsMlvVRk9UuiMkwQ4NlGlcQuZofk1hPhfeL7jVCwUYCGvKcm4Vw"
  )
  
  private val headers_24 = Map(
  		"Accept" -> "image/avif,image/webp,*/*",
  		"Sec-Fetch-Dest" -> "image",
  		"Sec-Fetch-Mode" -> "no-cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_25 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"content-type" -> "application/json;charset=utf-8",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IlBVVCIsImp0aSI6IjUxZjRiNzliLTk1OTMtNDZkOC1iNDhlLWQxOGM2YjRjYjkyNCIsImlhdCI6MTY4ODQyNjYzMX0.kAZ5lUI78KhDVsAAwrcRly7TdLvrdePuPjVrLaZeEFmq8vR30pJ91sloOZrB1dxmyH0se61VeEvQ5kg9WQLcsQ"
  )
  
  private val headers_26 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3BhYmxpdG8zMDAwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjVkOWZjNmU5LWM2YzItNDUyMS1iNzQzLTg3MjQ5Y2ExODE2ZSIsImlhdCI6MTY4ODQyNjYyOX0.Y3sBMbtuVJfw3ym6bfoygoAYNkFHjCxgZPiMailOje6460lmZQgoi38MswlicI88x1Gb1NWoHN17Yg3l9rCVsw"
  )
  
  private val headers_27 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjgwNTk2ZDViLWNjOGYtNDZhNS1hNjI3LTM2NGY1YjQ4NzQ5YyIsImlhdCI6MTY4ODQyNjYzMX0.Q37zGQq7E5eM-bwvnfUVvPf7--qXueL9JtrN_fS9ItXOc6n9IGCFAAsx1oS60krv1sMZEv1poX6Db_fwGQW71g"
  )
  
  private val headers_30 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjM4NDQwM2QxLTdjYTEtNDZkMi1iOTA1LWZlODE0YzlmMzllMCIsImlhdCI6MTY4ODQyNjYzMX0.eXyzhZS-MAVR8Yk6vr5pfHh0mrQwIMd5iv97ElNVP0oP7ClPTreHoj53Cb86sOqsfj27prRzQ9JqqsxR0znBRA"
  )
  
  private val headers_31 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6ImFmMmU3MGU3LWZhZjctNDBlYy04ZGJmLTJmMzI3OTgyMmFmMCIsImlhdCI6MTY4ODQyNjYzMn0.O46BkUij69BnI3FPoKsGIiLenAKUjHcNC9QL7pQDygaIHJTu9fXDls9K0Sjwae2r1HaGJbwFTBPqiz6KZHIa7Q"
  )
  
  private val headers_33 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"content-type" -> "application/json;charset=utf-8",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHVibGljL2xvbWFwL21hcmtlcnMuanNvbiIsImh0bSI6IlBVVCIsImp0aSI6ImNkMmFlNjkyLTdmZjktNDQxYy04ZWIzLTU2YWY4NDA1MTZhOSIsImlhdCI6MTY4ODQyNjYzOH0.4ODUTmbVQfrWuThb_uDCHOq96nhnkett8GLkLZrWodsMKw6rPRU73UIWcfRPNUZHIclD27OsnDpEePsjiYdT6w"
  )
  
  private val headers_34 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IkdFVCIsImp0aSI6IjJhNTJmOWJjLWFiNDgtNGUxMy1iMDRiLTRjNWYwNjBmNjcwMyIsImlhdCI6MTY4ODQyNjYzOH0.JIXcvA3g4-HCymkNvv1_pk5Du1gxdZAv2A2lExZ8vkVUTDMteP1G3EDHVRyje-fd5rU1n_df4qzr3uFezpONAg"
  )
  
  private val headers_35 = Map(
  		"Access-Control-Request-Headers" -> "authorization,content-type,dpop",
  		"Access-Control-Request-Method" -> "PUT",
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val headers_36 = Map(
  		"Origin" -> "https://localhost",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site",
  		"authorization" -> "DPoP eyJhbGciOiJSUzI1NiIsImtpZCI6IlJTZFhXUEplV0pJIn0.eyJpc3MiOiJodHRwczovL2lucnVwdC5uZXQiLCJhdWQiOiJzb2xpZCIsInN1YiI6Imh0dHBzOi8vdW8yNzYyMjAuaW5ydXB0Lm5ldC9wcm9maWxlL2NhcmQjbWUiLCJleHAiOjE2ODk2MzYyMjcsImlhdCI6MTY4ODQyNjYyNywianRpIjoiZDZkZDRhMjc5NDAzYzQ2ZiIsImNuZiI6eyJqa3QiOiJpZ0xBSGU5Y0FQMGlzd2o4YWQ1MXV1eFRCM1NxaDlvTENZYi1fNWV5bUlnIn0sImNsaWVudF9pZCI6ImQxZTkyODI4MDEyYzljNTFlZDAyNWFlNmU3YWY4ODg2Iiwid2ViaWQiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJvZmlsZS9jYXJkI21lIn0.Mvv84URv-x8YFxp1Hlv7JNdQ38pvDu1ajrqKS99rFSmxouO6zHDr1SkB0qtWYUUW0_mK4UnVluH-64npsBoVvs55D8JWyUN0WbBzgYWnNZ8y8KkVTtxOPbC08ULLUp64gpKER3O24OClRkcYoyvexn-TZ5T4mM2tTxGjSw8WAUH1s5m8tIMCuA3W1yepeT0IEIA6aBFHNW5dNMdTEMImjcl02Cfs8iP1LnBQ_MEh2hkkw9GB4xJjQjxYARN-uiT0tMT-qNe_HBkdY8yZ7skwAslqfVZNEHJzfEsNfaYZ61NQgidDXUuXKua10IiwumpEs8i-HbsR4IrJepm9HJWe8A",
  		"content-type" -> "application/json;charset=utf-8",
  		"dpop" -> "eyJhbGciOiJFUzI1NiIsImp3ayI6eyJjcnYiOiJQLTI1NiIsImt0eSI6IkVDIiwieCI6IkJfQnVYSFRqTWkxMFQzNjhlaG9NNXpsYWFFNERLbEV6OTFuQWRIVFZXVWciLCJ5IjoiZ3RjckFpOGRjVDRBZl92NE0wTHNhNzFfUE5SWktKTEJyUUlqeDN6YkkyWSIsImFsZyI6IkVTMjU2In0sInR5cCI6ImRwb3Arand0In0.eyJodHUiOiJodHRwczovL3VvMjc2MjIwLmlucnVwdC5uZXQvcHJpdmF0ZS9sb21hcC9sb21hcFVzZXIuanNvbiIsImh0bSI6IlBVVCIsImp0aSI6ImY5NTYzYzEwLTZhOWItNDUyMC04MTFlLTFhMWU1ZTBhYzMzMyIsImlhdCI6MTY4ODQyNjYzOX0.dE3oW74jMU4dBcQBuuYVLeKVBHC75h29W6XwhfyRJmOQF3LotDJnuwJiV0hR3dkNCtm2aAV8aEfsHjkjMdMDhA"
  )
  
  private val headers_37 = Map(
  		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,*/*;q=0.8",
  		"Sec-Fetch-Dest" -> "document",
  		"Sec-Fetch-Mode" -> "navigate",
  		"Sec-Fetch-Site" -> "none",
  		"Sec-Fetch-User" -> "?1",
  		"Upgrade-Insecure-Requests" -> "1"
  )
  
  private val headers_41 = Map(
  		"Content-Type" -> "application/json; charset=UTF-8",
  		"Sec-Fetch-Dest" -> "empty",
  		"Sec-Fetch-Mode" -> "cors",
  		"Sec-Fetch-Site" -> "cross-site"
  )
  
  private val uri1 = "https://maps.googleapis.com/maps/api/js"
  
  private val uri2 = "https://inrupt.net"
  
  private val uri3 = "https://pablito3000.inrupt.net/public/lomap/markers.json"
  
  private val uri5 = "https://uo276220.inrupt.net"
  
  private val uri6 = "https://www.googleapis.com/geolocation/v1/geolocate"

  private val scn = scenario("AddMarker")
    .exec(
      http("request_0")
        .get(uri2 + "/.well-known/openid-configuration")
        .headers(headers_0)
        .resources(
          http("request_1")
            .post(uri2 + "/register")
            .headers(headers_1)
            .body(RawFileBody("addmarker/0001_request.json")),
          http("request_2")
            .get(uri2 + "/.well-known/openid-configuration")
            .headers(headers_0),
          http("request_3")
            .get(uri2 + "/authorize?client_id=d1e92828012c9c51ed025ae6e7af8886&redirect_uri=https%3A%2F%2Flocalhost&response_type=code&scope=openid%20offline_access%20webid&state=72fa4fc14f904062922acbefa0562afe&code_challenge=QxOaX7hAw6lUk1JfvKdz5QoE1GRDgjS0HLDh9zJYshs&code_challenge_method=S256&prompt=consent&response_mode=query")
            .headers(headers_3),
          http("request_4")
            .get("/img/logo-no-background.png")
            .headers(headers_4),
          http("request_5")
            .get("/img/uk-flag.png")
            .headers(headers_5),
          http("request_6")
            .get(uri2 + "/.well-known/openid-configuration")
            .headers(headers_0),
          http("request_7")
            .post(uri2 + "/token")
            .headers(headers_7)
            .formParam("grant_type", "authorization_code")
            .formParam("redirect_uri", "https://localhost")
            .formParam("code", "69d9b194ce10128be9d04af57629baf8")
            .formParam("code_verifier", "7be14cd2f027470a8f024b7cfc3fae27b801807a16394b2fb3f25eefbe121afe4832a19d0d814634a81a20e6ecf337b1")
            .formParam("client_id", "d1e92828012c9c51ed025ae6e7af8886")
            .basicAuth("d1e92828012c9c51ed025ae6e7af8886","1d5a9978bc5677115dadb61e7279fb2f"),
          http("request_8")
            .get(uri2 + "/jwks")
            .headers(headers_8),
          http("request_9")
            .get(uri5 + "/profile/card")
            .headers(headers_9),
          http("request_10")
            .get(uri5 + "/profile/card")
            .headers(headers_9),
          http("request_11")
            .get(uri5 + "/profile/card")
            .headers(headers_9),
          http("request_12")
            .get("/img/statts.png")
            .headers(headers_12),
          http("request_13")
            .get(uri5 + "/profile/card")
            .headers(headers_9),
          http("request_14")
            .get(uri5 + "/public/lomap/markers.json")
            .headers(headers_14),
          http("request_15")
            .get(uri1 + "/AuthenticationService.Authenticate?1shttps%3A%2F%2Flocalhost%2Fmap&4sAIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&8b0&callback=_xdc_._nu08xv&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=82299")
            .headers(headers_15),
          http("request_16")
            .get(uri1 + "/GeocodeService.Search?5m2&1d0&2d0&7sES&9ses&callback=_xdc_._2bc7mp&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=36257")
            .headers(headers_15),
          http("request_17")
            .get(uri3)
            .headers(headers_17),
          http("request_18")
            .put(uri5 + "/public/lomap/markers.json")
            .headers(headers_18)
            .body(RawFileBody("addmarker/0018_request.txt")),
          http("request_19")
            .get(uri5 + "/public/lomap/markers.json")
            .headers(headers_19),
          http("request_20")
            .get(uri1 + "/ViewportInfoService.GetViewportInfo?1m6&1m2&1d43.326549494191745&2d-5.985528487011194&2m2&1d43.400444907611636&2d-5.749539908670251&2u15&4ses&5e0&6sm%40652000000&7b0&8e0&12e1&13shttps%3A%2F%2Flocalhost%2Fmap&14b1&callback=_xdc_._gynqso&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=114520")
            .headers(headers_15),
          http("request_21")
            .get("/img/marker.png")
            .headers(headers_12),
          http("request_22")
            .get("/img/home.png")
            .headers(headers_12),
          http("request_23")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_23),
          http("request_24")
            .get(uri5 + "/profile/yo.jpg")
            .headers(headers_24),
          http("request_25")
            .put(uri5 + "/public/lomap/markers.json")
            .headers(headers_25)
            .body(RawFileBody("addmarker/0025_request.txt")),
          http("request_26")
            .get(uri3)
            .headers(headers_26),
          http("request_27")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_27),
          http("request_28")
            .get(uri1 + "/GeocodeService.Search?5m2&1d43.36795114371874&2d0&7sES&9ses&callback=_xdc_._hpw5mt&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=123774")
            .headers(headers_15),
          http("request_29")
            .get(uri1 + "/GeocodeService.Search?5m2&1d43.36795114371874&2d-5.857479604958349&7sES&9ses&callback=_xdc_._6dxg7x&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=93726")
            .headers(headers_15),
          http("request_30")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_30),
          http("request_31")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_31)
        )
    )
    .pause(5)
    .exec(
      http("request_32")
        .get(uri1 + "/GeocodeService.Search?5m2&1d0&2d0&7sES&9ses&callback=_xdc_._2bc7mp&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=36257")
        .headers(headers_15)
        .resources(
          http("request_33")
            .put(uri5 + "/public/lomap/markers.json")
            .headers(headers_33)
            .body(RawFileBody("addmarker/0033_request.txt")),
          http("request_34")
            .get(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_34),
          http("request_35")
            .options(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_35),
          http("request_36")
            .put(uri5 + "/private/lomap/lomapUser.json")
            .headers(headers_36)
            .body(RawFileBody("addmarker/0036_request.txt"))
        )
    )
    .pause(2)
    .exec(
      http("request_37")
        .get("/map")
        .headers(headers_37)
        .resources(
          http("request_38")
            .get("/img/logo-no-background.png")
            .headers(headers_12),
          http("request_39")
            .get("/img/uk-flag.png")
            .headers(headers_12),
          http("request_40")
            .get(uri1 + "/AuthenticationService.Authenticate?1shttps%3A%2F%2Flocalhost%2Fmap&4sAIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&8b0&callback=_xdc_._nu08xv&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=82299")
            .headers(headers_15),
          http("request_41")
            .post(uri6 + "?key=AIzaSyB2h2OuRcUgy5N-5hsZqiPW6sH3n_rptiQ")
            .headers(headers_41),
          http("request_42")
            .get(uri1 + "/GeocodeService.Search?5m2&1d0&2d0&7sES&9ses&callback=_xdc_._2bc7mp&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=36257")
            .headers(headers_15),
          http("request_43")
            .get(uri1 + "/ViewportInfoService.GetViewportInfo?1m6&1m2&1d43.326549494191745&2d-5.985528487011194&2m2&1d43.400444907611636&2d-5.749539908670251&2u15&4ses&5e0&6sm%40652000000&7b0&8e0&12e1&13shttps%3A%2F%2Flocalhost%2Fmap&14b1&callback=_xdc_._gynqso&key=AIzaSyB59GV6ko6dRTY-oi3psvcsWngbFQ6RsGY&token=114520")
            .headers(headers_15),
          http("request_44")
            .get("/img/home.png")
            .headers(headers_12)
        )
    )

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
}
