const printResult = result => {
	result.forEach(file => {
		process.stdout.write(file.name)
		file.isDeleted && process.stdout.write(' [deleted]'.red.bold)
		file.isModified && process.stdout.write(' [modified]'.yellow.bold)
		file.isAdded && process.stdout.write(' [added]'.green.bold)
		file.isModified && process.stdout.write('\n' + file.changes)
		console.log()
	})
}

module.exports = printResult
