package lectures.`02_OOP`

object Enums extends App:
  enum Permissions:
    // constants representing the singleton values for Permissions
    case READ, WRITE, EXECUTE, NONE

    // can add fields/methods
    def openDocument(): Unit =
      if (this == READ) then
        println("opening document...")
      else
        println("reading not allowed.")

  val somePermissions: Permissions = Permissions.READ
  somePermissions.openDocument()
  println(s"Permissions.READ == somePermissions: ${Permissions.READ == somePermissions}")

  val somePermissions2 = Permissions.WRITE
  somePermissions2.openDocument()

  // constructor arguments
  enum PermissionsWithBits(bits: Int):
    case READ extends PermissionsWithBits(4)    // 100
    case WRITE extends PermissionsWithBits(2)   // 010
    case EXECUTE extends PermissionsWithBits(1) // 001
    case NONE extends PermissionsWithBits(0)    // 000

  // enums can have companion objects:
  //   - maybe contain factory methods
  object PermissionsWithBits:
    def fromBits(bits: Int): PermissionsWithBits =  // whatever
      PermissionsWithBits.NONE

  // standard API for enums
  val somePermissionsOrdinal = somePermissions.ordinal // 0 because defined first
  println("somePermissionsOrdinal: $somePermissionsOrdinal")

  val allPermissions = PermissionsWithBits.values // array of all possible values
  for
    perm <- allPermissions
  yield
    println(s"perm: $perm")

  val readPermission: Permissions = Permissions.valueOf("READ") // only available for enums without constructor parameters
  println(s"readPermisson: $readPermission")