import { Component, OnInit } from '@angular/core';
import { LazyLoadEvent } from 'primeng/api';
import { GroupService } from 'src/app/services/group.service';
import { RightAccessService } from 'src/app/services/right-access.service';
import { UserService } from 'src/app/services/user.service';
import { MessageService } from 'primeng/api';
import { ConfirmationService } from 'primeng/api';
import { ConfirmEventType } from 'primeng/api';
import { MenuService } from 'src/app/services/menu.service';

@Component({
  selector: 'app-group-management',
  templateUrl: './group-management.component.html',
  styleUrls: ['./group-management.component.css']
})
export class GroupManagementComponent implements OnInit {

  page: number = 0;
  groups: any;
  first = 0;
  rows = 10;
  totalRecords: number = 0;
  selectedGroups: any[] = [];
  selectAll: boolean = false;
  loading: boolean = true;
  cols: any[] = [];
  searchVal = '';
  displayMaximizable: boolean = false;
  isEdit: boolean = false;
  isAdd: boolean = false;
  row: any = {
  };
  selectedPermissions: any[] = [];

  permissions: any[] = [];

  checked: boolean = false;
  constructor(private menuService: MenuService, private confirmationService: ConfirmationService, private messageService: MessageService, private userService: UserService, private groupService: GroupService, private accessService: RightAccessService) { }

  ngOnInit(): void {
    // this.loadData({lazyEvent: JSON.stringify(event)});
    // this.selectedCategories = this.groups;
    // console.log(this.selectedCategories, 'ini cat')
    this.menuService.getMenu().subscribe(
      res => {
        this.permissions = res.data;
        console.log(this.permissions, 'ini this groups')
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
    return this.groups ? this.first === (this.groups.length - this.rows) : true;
  }

  isFirstPage(): boolean {
    return this.groups ? this.first === 0 : true;
  }

  loadData(event: LazyLoadEvent) {

    this.loading = true;
    setTimeout(() => {
      this.groupService.getGroupPagination(JSON.stringify(Math.floor(Number(event.first) / 4))).subscribe(
        res => {
          console.log(res)
          this.groups = res.data;
          this.totalRecords = res.totalRowCount;
          this.loading = false;
        }
      )
    }, 1000)
  }

  onSelectionChange(value = []) {
    this.selectAll = value.length === this.totalRecords;
    this.selectedGroups = value;
  }

  onSelectAllChange(event: any) {
    const checked = event.checked;

    if (checked) {
      this.groupService.getGroupPagination({ lazyEvent: JSON.stringify(event) }).subscribe(res => {
        this.selectedGroups = res.data;
        this.selectAll = true;
      });
    }
    else {
      this.selectedGroups = [];
      this.selectAll = false;
    }
  }

  showEditDialog(row: any) {
    this.isEdit = true;
    this.row = { ...row };
    console.log(this.row, ' in row')
    this.selectedPermissions = this.row.groups
    console.log(this.selectedPermissions)
    this.displayMaximizable = true;
  }

  openAddDialog() {
    this.selectedPermissions = []
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
    console.log(this.selectedPermissions, 'ini di post')
    console.log(this.row, 'ini post')
    this.userService.postUser(this.row).subscribe(
      res => {
        console.log(res);
        for (var i in this.selectedPermissions) {
          this.accessService.putAccess(res.data.userId, this.selectedPermissions[i].groupId, 'Y').subscribe()
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
        for (var i in this.selectedPermissions) {
          this.accessService.putAccess(res.data.userId, this.selectedPermissions[i].groupId, 'Y').subscribe();
        }
        this.showSuccess()
      }
    )
  }

  deleteUser(row: any) {
    this.row = { ...row }
    this.userService.deleteUser(row.userId).subscribe(
      res => {

      }
    )
  }

  deleteDialog(user: any) {
    this.confirmationService.confirm({
      message: 'Do you want to delete this record?',
      header: 'Delete Confirmation',
      icon: 'pi pi-info-circle',
      accept: () => {
        this.deleteUser(user)
        this.messageService.add({ severity: 'info', summary: 'Confirmed', detail: 'Record deleted' });
        window.location.reload()
      },
      reject: (type: ConfirmEventType) => {
        switch (type) {
          case ConfirmEventType.REJECT:
            this.messageService.add({ severity: 'warn', summary: 'Cancelled', detail: 'You have cancelled' });
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
