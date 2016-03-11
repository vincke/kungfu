// model 所使用的包名 (MappingKit 默认使用的包名)
String modelPackageName = "com.demo.common.model";
请问这个包名如何动态设定 ，包多了一层表名(小写去下划线)路径，想要的效果是
com.demo.common.model.blog.BaseBlog
com.demo.common.model.blog.Blog

请教下 @JFinal  这个JFinal2.1支持设置 或 扩展吗？

   生成这类 Model，主要是两个地方不同，
   一个是 packageName 不固定，
   二是生成文件保存的 savePath 不同，
   实现方式极度简单，只需要自建一个 MyModelGenerator extends ModelGenerator，
   覆盖其中的 genPackage(...)以及writeToFile(...) 两个方法，
   在原有的实现上让变量值变化一下，
   然后通过 generator.setModelGenerator(new MyModelGeneratory(...)) 
   覆盖掉系统提供的实现类即可。