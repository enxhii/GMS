package frontend.beans;

public enum RoleEnum {
	ADMIN {
		public String toString() {
			return "Admin";
		}
	},

	TRAINER {
		public String toString() {
			return "Trainer";
		}
	},
	MEMBER {
		public String toString() {
			return "Member";
		}
	}
}