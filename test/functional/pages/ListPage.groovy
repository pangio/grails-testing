package pages

import geb.Module

class ListPage extends ScaffoldPage {
	static url = "mall/index"
	
	static at = {
		title ==~ /Mall List/
	}
	
	static content = {
		newMallButton(to: CreatePage) { $("a", text: "New Mall") }
		mallTable { $("div.content table", 0) }
		mallRow { module MallRow, mallRows[it] }
		mallRows(required: false) { mallTable.find("tbody").find("tr") }
	}
}

class MallRow extends Module {
	static content = {
		cell { $("td", it) }
		cellText { cell(it).text() }
        cellHrefText{ cell(it).find('a').text() }
        address { cellText(0) }
		name { cellText(1) }
//        address { cellText(1) }
//		name { cellText(2) }
        showLink(to: ShowPage) { cell(0).find("a") }
	}
}