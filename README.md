## R&D : Generate asciidoctor/markdown report and store them on github

- Convert asciidoctor to github markdown flavored
```bash
asciidoctor -b docbook weekly-report-example.adoc
pandoc -f docbook -t gfm weekly-report-example.xml -o weekly-report-example.md
```

## Technology experimented and used

- [Asciidoctor java extension](https://github.com/asciidoctor/asciidoctorj/blob/v2.2.0/docs/integrator-guide.adoc): Guide to write asciidoctor extension
- [Markdowngenerator](https://github.com/Steppschuh/Java-Markdown-Generator): Java library to generate markdown

## Evaluated only

- [Markdown Java API](https://github.com/atlassian/commonmark-java): Java library for parsing and rendering CommonMark (Markdown)
- [Flexmark](https://github.com/vsch/flexmark-java): CommonMark/Markdown Java parser with source level AST 


