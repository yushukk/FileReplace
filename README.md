public static void main(String[] args) {
        
        FileReplace fileReplace = new FileReplace();
        
        //设置操作路径
        fileReplace.setPath("F://spaces//slite//theme//default");
        
        //设置操作文件类型
        fileReplace.setFileNameReg("middle.tpl");
        
        //设置操作类
        fileReplace.setContentAction(new ContentAction(){

            public String change(String content) {
                
                //设置替换规则
                return RegUtil.iterating("href\\s*=\\s*\"([^\"]*)\"",content,new RegIterator() {
                    
                    //设置每个具体匹配到的项目该怎么做
                    public String doOne(String matchStr) {
                        
                        // 获取href中的url
                        int start = matchStr.indexOf("\"");
                        
                        String url = matchStr.substring(start+1,matchStr.length()-1);
                        
                        //如果还未替换，在url两端加上东西
                        if(url!=null&&!"".equals(url)&&!url.contains("urlRewrite")&&!"#".equals(url)&&!"#this".equals(url)&&!url.startsWith("javascript")&&!url.startsWith("http")){
                            matchStr = "href=\"<@ylmp.urlRewrite url='"+url+"'></@ylmp.urlRewrite>\"";
                        }
                        
                        
                        return matchStr;
                    }
                });
                
            }
            
        });
        
        //执行操作
        fileReplace.execute();
        
    }