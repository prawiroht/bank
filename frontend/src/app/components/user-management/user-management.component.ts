import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { GroupService } from 'src/app/services/group.service';
import { RightAccessService } from 'src/app/services/right-access.service';
import { UserService } from 'src/app/services/user.service';
import { MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';
import { ConfirmEventType } from 'primeng/api';

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
  isAdd: boolean = false;
  row: any = {
  };
  selectedGroups: any[] = [];

  groups: any[] = [];

  checked: boolean = false;
  constructor(private confirmationService: ConfirmationService, private messageService: MessageService, private userService: UserService, private groupService: GroupService, private accessService: RightAccessService) { }

  ngOnInit(): void {
    // this.loadData({lazyEvent: JSON.stringify(event)});
    // this.selectedCategories = this.groups;
    // console.log(this.selectedCategories, 'ini cat')
    this.groupService.getGroup().subscribe(
      res => {
        this.groups = res.data;
        console.log(this.groups, 'ini this groups')
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
    this.selectedGroups = []
    console.log(this.row, 'ini row waktu buka')
    this.isAdd = true;
    this.displayMaximizable = true;
  }

  handleHideDialog() {
    this.row = {

    }
  }

  postUser() {
    this.row.isActive = 'N';
    console.log(this.selectedGroups, 'ini di post')
    console.log(this.row, 'ini post')
    this.userService.postUser(this.row).subscribe(
      res => {
        console.log(res);
        for (var i in this.selectedGroups) {
          this.accessService.putAccess(res.data.userId, this.selectedGroups[i].groupId, 'Y').subscribe()
        }
      }
    )
  }

  putUser() {
    console.log(this.row, 'ini row put')
    this.userService.putUser(this.row).subscribe(
      res => {
        for (var i in this.groups) {
          this.accessService.putAccess(res.data.userId, this.groups[i].groupId, 'N').subscribe();
        }
        for (var i in this.selectedGroups) {
          this.accessService.putAccess(res.data.userId, this.selectedGroups[i].groupId, 'Y').subscribe();
        }
        this.showSuccess()
      }
    )
  }

  deleteUser(row: any) {
    this.row = { ...row }
    this.userService.deleteUser(row.userId).subscribe(
      res => {
        this.showSuccess()
      }
    )
  }

  deleteDialog(user: any) {
    this.confirmationService.confirm({
      message: 'Do you want to delete this record?',
      header: 'Delete Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Record deleted' });
      },
      reject: (type: ConfirmEventType) => {
        switch (type) {
          case ConfirmEventType.REJECT:
            this.messageService.add({ severity: 'error', summary: 'Rejected', detail: 'You have rejected' });
            break;
          case ConfirmEventType.CANCEL:
            this.messageService.add({ severity: 'warn', summary: 'Cancelled', detail: 'You have cancelled' });
            break;
        }
      }
    });
  }

  showSuccess() {
    this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Message Content' });
    window.location.reload()
  }
}
