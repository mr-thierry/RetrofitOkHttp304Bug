# RetrofitOkHttp304Bug

Used to show an example of the issue listed here:
https://github.com/square/retrofit/issues/3191#issuecomment-519523705

To replicate the problem
1) Start the app
2) Make an HTTP call by pressing the button
3) Wait 60 seconds for the local cache to expire
4) Make an HTTP call again by pressing the button again. The server should return a 304, which Retrofit cannot parse
--> Retrofit tries to parse the result, but the result is empty

