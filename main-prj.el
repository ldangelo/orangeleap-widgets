(jde-project-file-version "1.0")
(jde-set-variables
 '(jde-project-name "orangeleap-donatenow")
 '(jdibug-connect-host (quote "localhost"))
 '(jdibug-connect-port 8000)
 '(jde-db-option-connect-socket '(nil "8000"))
 '(jde-ant-buildfile "/Users/ldangelo/Development/orangeleap/orangeleap-donatenow/build/build.xml")
 '(jde-ant-enable-find nil)
 '(jde-ant-read-target t)
 '(jde-ant-home "/usr/share/ant")
 '(jde-ant-invocation-method (quote ("Java")))
 ;; Set name for your make program: ant or maybe maven?
 '(jde-build-function 'jde-maven2-build)
 '(compilation-directory "//Users/ldangelo/Development/orangeleap/orangeleap-donatenow")
'(jde-maven-project-file-name "pom.xml")
 '(jde-jdk-registry (quote (("1.6" . "/System/Library/Frameworks/JavaVM.framework/Versions/1.6.0/"))))
 '(jde-run-working-directory "/Users/ldangelo/Development/orangeleap/orangeleap-donatenow/")
 '(jde-compile-option-directory "/Users/ldangelo/Development/orangeleap/orangeleap-donatenow/target/classes") 
 '(jde-compile-option-target (quote ("1.6")))
 '(jde-jdk (quote ("1.6")))

 ;; Nice feature sorting imports.
;; '(jde-import-auto-sort t)

 ;; For syntax highlighting and basic syntax checking parse buffer
 ;; number of seconds from the time you changed the buffer.
;; '(jde-auto-parse-buffer-interval 600)

 ;; Only for CygWin users it improves path resolving
;; '(jde-cygwin-path-converter (quote (jde-cygwin-path-converter-cygpath)))
 ;; You can set different user name and e-mail address for each project
 '(user-mail-address "ldangelo@orangeleap.com") )

(jde-maven2-set-current-profile "jetty")
(jde-maven2-set-current-goal "jetty:run")
(jde-maven2-set-current-arguments "-Dmaven.test.skip=false")





