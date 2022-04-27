import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { GroupService } from 'src/app/services/group.service';
import { UserService } from 'src/app/services/user.service';


@Component({
  selector: 'app-user-management',
  templateUrl: './user-management.component.html',
  styleUrls: ['./user-management.component.css']
})
export class UserManagementComponent implements OnInit {
  page: number = 0;
  users: any;
  first = 0;
  rows = 10;
  totalRecords: number = 0;
  selectedUsers: any[] = [];
  selectAll: boolean = false;
  loading: boolean = true;
  cols: any[] = [];
  searchVal = '';
  displayMaximizable: boolean = false;
  isEdit: boolean = false;
  isAdd: boolean = true;
  row: any = {
  };
  selectedGroups: any[] = [];

  groups: any[] = [];

  checked: boolean = false;
  constructor(private userService: UserService, private groupService: GroupService) { }

  ngOnInit(): void {
    // this.loadData({lazyEvent: JSON.stringify(event)});
    // this.selectedCategories = this.groups;
    // console.log(this.selectedCategories, 'ini cat')
    this.groupService.getGroup().subscribe(
      res => {
        this.groups = res.data;
        console.log(this.groups)
      }
    )
  }

  next() {
    this.first = this.first + this.rows;
  }

  prev() {
    this.first = this.first - this.rows;
  }

  reset() {
    this.first = 0;
  }

  isLastPage(): boolean {
    return this.users ? this.first === (this.users.length - this.rows) : true;
  }

  isFirstPage(): boolean {
    return this.users ? this.first === 0 : true;
  }

  loadData(event: LazyLoadEvent) {

    this.loading = true;
    setTimeout(() => {
      this.userService.getUser(JSON.stringify(Math.floor(Number(event.first) / 4))).subscribe(
        res => {
          console.log(res)
          this.users = res.data;
          this.totalRecords = res.totalRowCount;
          this.loading = false;
        }
      )
    }, 1000)
  }

  onSelectionChange(value = []) {
    this.selectAll = value.length === this.totalRecords;
    this.selectedUsers = value;
  }

  onSelectAllChange(event: any) {
    const checked = event.checked;

    if (checked) {
      this.userService.getUser({ lazyEvent: JSON.stringify(event) }).subscribe(res => {
        this.selectedUsers = res.data;
        this.selectAll = true;
      });
    }
    else {
      this.selectedUsers = [];
      this.selectAll = false;
    }
  }

  showEditDialog(row: any) {
    this.isEdit = true;
    this.row = { ...row };
    console.log(this.row, ' in row')
    this.selectedGroups = this.row.groups
    console.log(this.selectedGroups)
    this.displayMaximizable = true;
  }

  openAddDialog() {
    console.log(this.row, 'ini row waktu buka')
    this.isAdd = true;
    this.displayMaximizable = true;
  }

  handleHideDialog() {
    this.row = {
      username: ''
    }
  }

  postUser() {
    this.row.isActive = 'N';
    console.log(this.selectedGroups, 'ini di post')
    console.log(this.row, 'ini post')
    this.userService.postUser(this.row).subscribe(
      res => {
        console.log(res)
      }
    )
  }
}
