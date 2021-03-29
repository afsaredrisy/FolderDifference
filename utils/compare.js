const diff = require('diff')
const fs = require('fs')
const path = require('path')

const compareFiles = (filePath1, filePath2, extName) => {
	let isModified = false
	let differences = ''
	let changes = ''

	const file1 = fs.readFileSync(filePath1, {
		encoding: 'utf-8',
	})
	const file2 = fs.readFileSync(filePath2, {
		encoding: 'utf-8',
	})

	switch (extName) {
		case '.json':
			changes = diff.diffJson(file1, file2)
			break
		case '.txt':
			changes = diff.diffChars(file1, file2)
			break

		default:
			changes = diff.diffLines(file1, file2)
			break
	}

	changes.forEach(part => {
		// green for additions, red for deletions
		// grey for common parts
		let color = ''
		if (part.added) {
			color += 'green'
			isModified = true
		} else if (part.removed) {
			color += 'red'
			isModified = true
		} else {
			color += 'grey'
		}
		differences += part.value[color]
	})

	return { differences, isModified }
}

const compareFolders = (folderPath1, folderPath2) => {
	let result = []

	if (!fs.existsSync(folderPath1) || !fs.existsSync(folderPath2)) {
		console.log('Please provide valid path for the folders'.bold.white)
		process.exit(1)
	}

	const folder1 = fs.readdirSync(folderPath1)
	const folder2 = fs.readdirSync(folderPath2)

	folder1.forEach(file => {
		if (folder2.includes(file)) {
			result = [...result, { name: file, isModified: false, isDeleted: false }]
		} else {
			result = [...result, { name: file, isModified: false, isDeleted: true }]
		}
	})

	folder2.forEach(file => {
		if (folder1.includes(file)) {
			const filePath1 = folderPath1 + `/${file}`
			const filePath2 = folderPath2 + `/${file}`
			const extName = path.extname(file)

			const { differences, isModified } = compareFiles(
				filePath1,
				filePath2,
				extName
			)

			if (isModified) {
				result = result.map(child => {
					if (child.name === file) {
						child.changes = differences
						child.isModified = true
						child.isDeleted = false
						child.isAdded = false
					}
					return child
				})
			}
			return
		}
		result = [
			...result,
			{ name: file, isModified: false, isDeleted: false, isAdded: true },
		]
	})

	return result
}

module.exports = { compareFiles, compareFolders }
