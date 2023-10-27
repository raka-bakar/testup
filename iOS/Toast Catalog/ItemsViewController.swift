import UIKit

class ItemsViewController: UITableViewController {

    private lazy var dataSource = makeDataSource()

    override func viewDidLoad() {
        super.viewDidLoad()

        tableView.allowsSelection = false
        tableView.register(ItemTableViewCell.self, forCellReuseIdentifier: ItemTableViewCell.reuseIdentifier)
        tableView.dataSource = dataSource
    }

    func makeDataSource() -> UITableViewDiffableDataSource<Int, Item> {
        UITableViewDiffableDataSource(
            tableView: tableView,
            cellProvider: {  tableView, indexPath, item in
                let cell = tableView.dequeueReusableCell(
                    withIdentifier: ItemTableViewCell.reuseIdentifier,
                    for: indexPath
                ) as? ItemTableViewCell

                cell?.nameLabel.text = item.name
                cell?.priceLabel.text = item.price

                return cell
            }
        )
    }

    func update(with items: [Item], animate: Bool = true) {
        var snapshot = NSDiffableDataSourceSnapshot<Int, Item>()
        snapshot.appendSections([0])
        snapshot.appendItems(items, toSection: 0)
        dataSource.apply(snapshot, animatingDifferences: animate)
    }

}
