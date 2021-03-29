require('colors')
const { compareFolders } = require('./utils/compare')
const printResult = require('./utils/printResult')

if (!process.argv[2] || !process.argv[3]) {
	console.log('Both folder path is required'.red)
	process.exit(1)
}

const folderPath1 = process.argv[2]
const folderPath2 = process.argv[3]

const result = compareFolders(folderPath1, folderPath2)
printResult(result)
