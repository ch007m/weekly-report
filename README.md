## R&D : Generate asciidoctor/markdown report and store then on github

- Convert asciidoctor to github markdown flavored
```bash
asciidoctor -b docbook weekly-report-example.adoc
pandoc -f docbook -t gfm weekly-report-example.xml -o weekly-report-example.md
```

## Tools

- Asciidoctor java: https://github.com/asciidoctor/asciidoctorj/blob/v2.2.0/docs/integrator-guide.adoc
- Markdown Java API: https://github.com/atlassian/commonmark-java

