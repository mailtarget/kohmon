# Kohmon

Collection of kotlin common helper

#### Kohmon Core

this module contains simple helpers for strings, regex, coroutine and date

### TODO

- Kohmon Json
- Kohmon JodaTime
- Kohmon Vertx
- Kohmon Morphia
- Kohmon Ktor
- Kohmon Inject
- Kohmon SQL
...

### Usage

Since it was a private repo, to use this module to your project, add repository as follows

    repositories {
        maven {
            url 'https://maven.pkg.github.com/mailtarget/kohmon'
            credentials {
                username = System.getenv("GITHUB_USERNAME")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    
    dependencies {
        implementation "co.mtarget:kohmon-core:0.0.1"
    }
    
you need add GITHUB_USERNAME and GITHUB_TOKEN to your computer's environment variables, and add dependencies