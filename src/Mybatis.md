# Mybatis

一个基于Java的持久层框架

ORM(Object Relation Mapping) 对象关系映射



## #{}

在Mybatis中，有两种占位符

‘# {}’解析传递进来的参数数据
${}对传递进来的参数原样拼接在SQL中

‘#{}’是预编译处理，${}是字符串替换。
使用#{}可以有效的防止SQL注入，提高系统安全性

实际上就是调用了实体的get方法

## 动态sql
 
查询      

        <where>
        			<if test="name!=null">
        				and name=#{name}
        			</if>
        			<if test="sal!=null">
        				and sal < #{sal}
        			</if>
        		</where>

更新 

        	<!--动态更新-->
        	<!--不要忘了逗号-->
        	<update id="updateByConditions" parameterType="map">
        
        		update students
        		<set>
        			<if test="name!=null">
        				 name = #{name},
        			</if>
        			<if test="sal!=null">
        				 sal = #{sal},
        			</if>
        		</set>
        		where id = #{id}
        	</update>
    
    
删除 

        	<delete id="deleteByConditions" parameterType="int">
        
        		<!-- foreach用于迭代数组元素
        			 open表示开始符号
        			 close表示结束符合
        			 separator表示元素间的分隔符
        			 item表示迭代的数组，属性值可以任意，但提倡与方法的数组名相同
        			 #{ids}表示数组中的每个元素值
        		 -->
        		delete from students where id in
        		 <foreach collection="array" open="(" close=")" separator="," item="ids">
        			 #{ids}
        		 </foreach>
        
        	</delete>


插入

        <!--SQL片段默认是不帮我们自动生成合适的SQL，因此需要我们自己手动除去逗号-->
            <sql id="key">
                <trim suffixOverrides=",">
                    <if test="id!=null">
                        id,
                    </if>
        
                    <if test="id!=null">
                        name,
                    </if>
        
                    <if test="id!=null">
                        sal,
                    </if>
                </trim>
            </sql>
        
            <sql id="value">
                <trim suffixOverrides=",">
                    <if test="id!=null">
                        #{id},
                    </if>
        
                    <if test="id!=null">
                        #{name},
                    </if>
        
                    <if test="id!=null">
                        #{sal},
                    </if>
                </trim>
            </sql>
            <!--动态插入-->
            <insert id="insertByConditions" parameterType="zhongfucheng.Student">
        	
        		insert into students (<include refid="key"/>) values
                (<include refid="value"/>)
        
        	</insert>

## 入门总结

Mybatis的准备工作与Hibernate差不多，都需要一个总配置文件、一个映射文件。  
Mybatis的SQLSession工具类使用ThreadLocal来对线程中的Session来进行管理。  
Mybatis的事务默认是开启的，需要我们手动去提交事务。  
Mybatis的SQL语句是需要手写的，在程序中通过映射文件的命名空间.sql语句的id来进行调用!  
在Mybatis中，增删改查都是Mybatis需要我们自己写SQL语句的，然后在程序中调用即可了。SQL由于是我们自己写的，于是就相对Hibernate灵活一些。  
如果需要传入多个参数的话，那么我们一般在映射文件中用Map来接收。  
由于我们在开发中会经常用到条件查询，在之前，我们是使用查询助手来帮我们完成对SQL的拼接的。而Mybatis的话，我们是自己手写SQL代码的。  
Mybatis也支持一些判断标签，于是我们就可以通过这些标签来完成动态CRUD的操作了。  
值得注意的是，我们的sql片段代码是需要我们自己手动去分割，号的。  


## 生成主键

          <!-- mysql的uuid生成主键 -->
          <insert id="insertUser" parameterType="cn.mybatis.po.User">
            <selectKey keyProperty="id" order="BEFORE" resultType="string">
              select uuid()
            </selectKey>
            
            INSERT INTO USER(id,username,birthday,sex,address) VALUES(#{id},#{username},#{birthday},#{sex},#{address})
          </insert> 


## 主键返回 

        mysql:
        
          <insert id="insertUser" parameterType="cn.mybatis.po.User">
            <selectKey keyProperty="id" order="AFTER" resultType="int">
              select LAST_INSERT_ID()
            </selectKey>
            INSERT INTO USER(username,birthday,sex,address) VALUES(#{username},#{birthday},#{sex},#{address})
          </insert>
          
## resultMap和resultType区别

resultType ：指定输出结果的类型（pojo、简单类型、hashmap..），将sql查询结果映射为java对象 。

使用resultType注意：sql查询的列名要和resultType指定pojo的属性名相同，  
指定相同 属性方可映射成功，如果sql查询的列名要和resultType指定pojo的属性名全部不相同，list中无法创建pojo对象的。

resultMap：将sql查询结果映射为java对象。

如果sql查询列名和最终要映射的pojo的属性名不一致，使用resultMap将列名和pojo的属性名做一个对应关系 （列名和属性名映射配置）


resultType：

作用：将查询结果按照sql列名pojo属性名一致性映射到pojo中。  
场合：常见一些明细记录的展示，将关联查询信息全部展示在页面时，此时可直接使用resultType将每一条记录映射到pojo中，  
在前端页面遍历list（list中是pojo）即可。

resultMap：

使用association和collection完成一对一和一对多高级映射。


association：

作用：将关联查询信息映射到一个pojo类中。
场合：为了方便获取关联信息可以使用association将关联订单映射为pojo，比如：查询订单及关联用户信息。  


collection：

作用：将关联查询信息映射到一个list集合中。
场合：为了方便获取关联信息可以使用collection将关联信息映射到list集合中，比如：查询用户权限范围模块和功能，可使用collection将模块和功能列表映射到list中。


## Mybatis映射文件处理特殊字符

第一种方法：

用了转义字符把>和<替换掉就OK了

第二种方法：

<![CDATA[ ]]>



## 延迟加载

在进行数据查询时，为了提高数据库查询性能，尽量使用单表查询，因为单表查询比多表关联查询速度要快。

如果查询单表就可以满足需求，一开始先查询单表，当需要关联信息时，再关联查询，当需要关联信息再查询这个叫延迟加载。

在Mybatis中延迟加载就是在resultMap中配置具体的延迟加载..

          <!-- 全局配置参数 -->
          <settings>
            <!-- 延迟加载总开关 -->
            <setting name="lazyLoadingEnabled" value="true" />  
            <!-- 设置按需加载 -->
            <setting name="aggressiveLazyLoading" value="false" />
          </settings>


## Mybatis 缓存
Mybatis 中有一级缓存和二级缓存，默认情况下一级缓存是开启的，而且是不能关闭的。一级缓存  
是指 SqlSession 级别的缓存，当在同一个 SqlSession 中进行相同的 SQL 语句查询时，第二次以  
后的查询不会从数据库查询，而是直接从缓存中获取，一级缓存最多缓存 1024 条 SQL。二级缓存  
是指可以跨 SqlSession 的缓存。是 mapper 级别的缓存，对于 mapper 级别的缓存不同的  
sqlsession 是可以共享的。

### 一级缓存的生命周期有多长

MyBatis在开启一个数据库会话时，会 创建一个新的SqlSession对象，SqlSession对象中会有一个新的Executor对象，Executor对象中持有一个新的PerpetualCache对象；  
当会话结束时，SqlSession对象及其内部的Executor对象还有PerpetualCache对象也一并释放掉。  
如果SqlSession调用了close()方法，会释放掉一级缓存PerpetualCache对象，一级缓存将不可用；  
如果SqlSession调用了clearCache()，会清空PerpetualCache对象中的数据，但是该对象仍可使用；  
SqlSession中执行了任何一个update操作(update()、delete()、insert()) ，都会清空PerpetualCache对象的数据，但是该对象可以继续使用；

### SqlSession 一级缓存的工作流程：

对于某个查询，根据statementId,params,rowBounds来构建一个key值，根据这个key值去缓存Cache中取出对应的key值存储的缓存结果​  
判断从Cache中根据特定的key值取的数据数据是否为空，即是否命中；​  
如果命中，则直接将缓存结果返回；​  
如果没命中：  
    &emsp; 去数据库中查询数据，得到查询结果；  
    &emsp; 将key和查询到的结果分别作为key,value对存储到Cache中；  
    &emsp;  将查询结果返回；

### 二级缓存：
二级缓存是用来解决一级缓存不能跨会话共享的问题的，  
范围是namespace 级别的，可以被多个SqlSession 共享（只要是同一个接口里面的相同方法，都可以共享），  
生命周期和应用同步。如果你的MyBatis使用了二级缓存，  
并且你的Mapper和select语句也配置使用了二级缓存，  
那么在执行select查询的时候，MyBatis会先从二级缓存中取输入，  
其次才是一级缓存，即MyBatis查询数据的顺序是：  
二级缓存   —> 一级缓存 —> 数据库。

###  禁用二级缓存
对于变化频率较高的sql，需要禁用二级缓存：

在statement中设置useCache=false可以禁用当前select语句的二级缓存，即每次查询都会发出sql去查询，默认情况是true，即该sql使用二级缓存。、、

    <select id="findOrderListResultMap" resultMap="ordersUserMap" useCache="false">

## ybatis和ehcache缓存框架整合
ehcache是专门用于管理缓存的，Mybatis的缓存交由ehcache管理会更加得当..

在mybatis中提供一个cache接口，只要实现cache接口就可以把缓存数据灵活的管理起来。重写Cache接口