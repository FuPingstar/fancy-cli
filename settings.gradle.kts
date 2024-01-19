pluginManagement {
    // 插件存储库
    repositories {
        gradlePluginPortal()
        maven(url = "https://repo.spring.io/plugins-release")
    }
}

dependencyResolutionManagement {
    repositories {
        // 查找maven本地仓库顺序：
        // 1. ~/.m2/settings.xml
        // 2. M2_HOME/settings.xml
        // 3. ~/.m2/conf/repositorys
        // 配置本地maven仓库会拖慢构建速度
        mavenLocal()
        maven {
            url = uri("https://maven.aliyun.com/repository/public/")
        }
        maven {
            url = uri("https://maven.aliyun.com/repository/spring/")
        }
        // https://repo.maven.apache.org/maven2/
        mavenCentral()
        maven {
            url = uri("https://repo.spring.io/release")
        }
    }
}

rootProject.name = "fancy-cli"
include("demo")

